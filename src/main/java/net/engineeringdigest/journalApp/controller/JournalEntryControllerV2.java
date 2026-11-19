package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
//        List<JournalEntry> all = journalEntryService.getAll();
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry (@RequestBody JournalEntry myEntry,@PathVariable String userName){
        try{
//            myEntry.setDate(LocalDateTime.now());
            User user = userService.findByUserName(userName);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            journalEntryService.saveEntry(myEntry,userName);
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry =  journalEntryService.findById(myId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName){
        journalEntryService.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId id,@PathVariable String userName, @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() :old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() :old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        journalEntryService.saveEntry(old);
//        return old;
    }
}
