package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    TimeEntryRepository repository;

    public TimeEntryController(TimeEntryRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry newEntry = repository.create(timeEntryToCreate);
        return new ResponseEntity<TimeEntry>(newEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry newEntry = repository.find(timeEntryId);
        if (newEntry != null) {
            return new ResponseEntity<TimeEntry>(newEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = repository.list();
        return new ResponseEntity<List<TimeEntry>>(list, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry newEntry = repository.update(timeEntryId, timeEntry);
        if (newEntry != null) {
            return new ResponseEntity<TimeEntry>(newEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        repository.delete(timeEntryId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
