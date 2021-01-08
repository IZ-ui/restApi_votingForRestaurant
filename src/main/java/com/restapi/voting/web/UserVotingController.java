package com.restapi.voting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.restapi.voting.AuthorizedUser;
import com.restapi.voting.model.Dish;
import com.restapi.voting.model.User;
import com.restapi.voting.model.Vote;
import com.restapi.voting.repository.DishRepository;
import com.restapi.voting.repository.UserRepository;
import com.restapi.voting.repository.VoteRepository;
import com.restapi.voting.util.VoteUtil;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.restapi.voting.util.ValidationUtil.*;

@RestController
@RequestMapping(value = UserVotingController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVotingController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/profile/votes";

    private final DishRepository dishRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    public UserVotingController(DishRepository dishRepository, VoteRepository voteRepository, UserRepository userRepository) {
        this.dishRepository = dishRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    @Cacheable("dish")
    @GetMapping("/restaurants/{id}/dishes")
    public List<Dish> getAllByRestaurantIdToday(@PathVariable int id) {
        log.info("get all today's dishes by restaurant id {}", id);
        return dishRepository.findAllByRestaurantIdToday(id, LocalDate.now());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Vote> create(@RequestBody Vote vote, @AuthenticationPrincipal AuthorizedUser authUser) {
        Assert.notNull(vote, "vote must not be null");
        vote.setUser(getUser(authUser));
        checkNew(vote);
        log.info("save vote {} for restaurant id{} by User id{}", vote, vote.getRestaurantId(), vote.getUser().getId());
        Vote created = voteRepository.save(vote);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Vote update(@RequestBody Vote vote, @PathVariable int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        LocalDate date = LocalDate.now();
        int userId = authUser.getId();
        assureIdConsistent(vote, id);
        log.info("update {} for user {}", vote, userId);
        Assert.notNull(vote, "vote must not be null");
        int restaurantId = vote.getRestaurantId();
        Vote voted = voteRepository.findByUserIdAndDate(userId, date);

        if (voted == null) {
            voted = new Vote(restaurantId, date);
            voted.setUser(getUser(authUser));
        } else {
            checkVoteOnTime(LocalTime.now(VoteUtil.getClock()));
            voted.setRestaurantId(vote.getRestaurantId());
        }

        return checkNotFoundWithId(voteRepository.save(voted), id);
    }

    @GetMapping
    public List<Vote> getAllVotes(@AuthenticationPrincipal AuthorizedUser authUser) {
        int userId = authUser.getId();
        log.info("get all votes for user {}", userId);
        return voteRepository.findAllByUserId(userId);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Vote get(@PathVariable int id) {
        log.info("get vote with id {} ", id);
        return checkNotFoundWithId(voteRepository.findById(id).orElse(null), id);
    }

    private User getUser(AuthorizedUser authUser) {
        return userRepository.findById(authUser.getId()).orElse(null);
    }
}