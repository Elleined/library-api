package com.denielle.api.restapi;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.dto.BookDTO;
import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.model.Book;
import com.denielle.api.restapi.service.AuthorService;
import com.denielle.api.restapi.service.BookService;
import com.denielle.api.restapi.service.GenreService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AfterStartUp {
    private static final LocalDateTime now = LocalDateTime.now();

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @PostConstruct
    public void init() {
        if (genreService.isNameAlreadyExists("Drama")) {
            log.debug("Returning Because Initial Database record are already saved!");
            return;
        }

        genreService.saveAll(this.genres());
        log.debug("Initial genres record saved");
        authorService.saveAll(this.authors());
        log.debug("Initial authors record saved");
        bookService.saveAll(this.books());
        log.debug("Initial books record saved");
    }

    private List<String> genres() {
        return List.of("Drama",
                "Comedy",
                "Action",
                "Horror",
                "Romance",
                "Thriller",
                "Fantasy",
                "Fiction",
                "Adventure",
                "Mystery",
                "Crime",
                "War",
                "Musical",
                "Sports",
                "Science Fiction",
                "Literary",
                "Suspense",
                "Graphic Novel",
                "Speculative Fiction",
                "Historical",
                "General Fiction"
        );
    }

    private List<AuthorDTO> authors() {
        return List.of(
                AuthorDTO.builder()
                        .name("Bret Easton Ellis")
                        .biography("Bret Easton Ellis is an American author, screenwriter, short-story writer, and director. Ellis was first regarded as one of the so-called literary Brat Pack and is a self-proclaimed satirist whose trademark technique, as a writer, is the expression of extreme acts and opinions in an affectless style.")
                        .createdAt(now)
                        .build(),

                AuthorDTO.builder()
                        .name("Stephen King")
                        .biography("Stephen Edwin King is an American author of horror, supernatural fiction, suspense, crime, science-fiction, and fantasy novels. Described as the 'King of Horror', his books have sold more than 350 million copies as of 2006, and many have been adapted into films, television series, miniseries, and comic books.")
                        .createdAt(now)
                        .build(),

                AuthorDTO.builder().name("Mario Puzo")
                        .biography("Mario Francis Puzo was an American author, screenwriter, and journalist. He is known for his crime novels about the Italian-American Mafia and Sicilian Mafia, most notably The Godfather, which he later co-adapted into a film trilogy directed by Francis Ford Coppola.")
                        .createdAt(now)
                        .build(),

                AuthorDTO.builder()
                        .name("William Thomas Harris III")
                        .biography("William Thomas Harris III is an American writer, best known for a series of suspense novels about his most famous character, Hannibal Lecter.")
                        .createdAt(now)
                        .build(),

                AuthorDTO.builder()
                        .name("Nelle Harper Lee")
                        .biography("Nelle Harper Lee was an American novelist. She wrote the 1960 novel To Kill a Mockingbird that won the 1961 Pulitzer Prize and became a classic of modern American literature.")
                        .createdAt(now)
                        .build(),

                AuthorDTO.builder()
                        .name("John Ronald Reuel Tolkien")
                        .biography("John Ronald Reuel Tolkien CBE FRSL was an English writer and philologist. He was the author of the high fantasy works The Hobbit and The Lord of the Rings. From 1925 to 1945, Tolkien was the Rawlinson and Bosworth Professor of Anglo-Saxon and a Fellow of Pembroke College, both at the University of Oxford. ")
                        .createdAt(now)
                        .build(),

                AuthorDTO.builder()
                        .name("J.K Rowling")
                        .biography("Joanne Rowling CH OBE FRSL, best known by her pen name J. K. Rowling, is a British author and philanthropist. She wrote Harry Potter, a seven-volume children's fantasy series published from 1997 to 2007.")
                        .createdAt(now)
                        .build(),

                AuthorDTO.builder()
                        .name("L. Frank Baum")
                        .biography("Lyman Frank Baum was an American author best known for his children's books, particularly The Wonderful Wizard of Oz, part of a series. In addition to the 14 Oz books, Baum penned 41 other novels, 83 short stories, over 200 poems, and at least 42 scripts.")
                        .createdAt(now)
                        .build(),

                AuthorDTO.builder()
                        .name("Margaret Mitchell")
                        .biography("Margaret Munnerlyn Mitchell was an American novelist and journalist. Mitchell wrote only one novel, published during her lifetime, the American Civil War-era novel Gone with the Wind, for which she won the National Book Award for Fiction for Most Distinguished Novel of 1936 and the Pulitzer Prize for Fiction in 1937.")
                        .createdAt(now)
                        .build(),

                AuthorDTO.builder()
                        .name("William Goldman")
                        .biography("William Goldman was an American novelist, playwright, and screenwriter. He first came to prominence in the 1950s as a novelist before turning to screenwriting. He won Academy Awards for his screenplays Butch Cassidy and the Sundance Kid and All the President's Men.")
                        .createdAt(now)
                        .build()
        );
    }

    public List<BookDTO> books() {
        return List.of(
                BookDTO.builder()
                        .title("Imperial Bedrooms")
                        .description("Clay, a successful screenwriter, has returned from New York to Los Angeles to help cast his new movie, and hes soon drifting through a long-familiar circle. Blair, his former girlfriend, is married to Trent, an influential manager whos still a bisexual philanderer, and their Beverly Hills parties attract various levels of fame, fortune and power. Then theres Clays childhood friend Julian, a recovering addict, and their old dealer, Rip, face-lifted beyond recognition and seemingly even more sinister than in his notorious past.")
                        .isbn("9780307278692")
                        .pages(192)
                        .publishedDate(LocalDate.of(2010, 6, 1))
                        .createdAt(now)
                        .authorName("Bret Easton Ellis")
                        .genres(List.of("Literary"))
                        .build(),

                BookDTO.builder()
                        .title("The Shards")
                        .description("17-year-old Bret is a senior at the exclusive Buckley prep school when a new student arrives with a mysterious past. Robert Mallory is bright, handsome, charismatic, and shielding a secret from Bret and his friends even as he becomes a part of their tightly knit circle. Bret's obsession with Mallory is equaled only by his increasingly unsettling pre-occupation with The Trawler, a serial killer on the loose who seems to be drawing ever closer to Bret and his friends, taunting them -- and Bret in particular -- with grotesque threats and horrific, sharply local acts of violence. The coincidences are uncanny, but they are also filtered through the imagination of a teenager whose gifts for constructing narrative from the filaments of his own life are about to make him one of the most explosive literary sensations of his generation. Can he trust his friends -- or his own mind -- to make sense of the danger they appear to be in? Thwarted by the world and by his own innate desires, buffeted by unhealthy fixations, he spirals into paranoia and isolation as the relationship between The Trawler and Robert Mallory hurtles inexorably toward a collision.")
                        .isbn("9780593535608")
                        .pages(608)
                        .publishedDate(LocalDate.of(2023, 1, 1))
                        .createdAt(now)
                        .authorName("Bret Easton Ellis")
                        .genres(List.of("General Fiction", "Suspense", "Thriller", "Literary"))
                        .build(),

                BookDTO.builder()
                        .title("The Gunslinger Born")
                        .description("The man in black fled across the desert, and the gunslinger followed.\"\" With those words, millions of readers were introduced to Stephen King''s Roland - an implacable gunslinger in search of the enigmatic Dark Tower, powering his way through a dangerous land filled with ancient technology and deadly magic. Now, in a comic book personally overseen by King himself, Roland''s past is revealed! Sumptuously drawn by Jae Lee and Richard Isanove, adapted by long-time Stephen King expert Robin Furth (author of Stephen King''s The Dark Tower: A Concordance) and scripted by New York Times bestseller Peter David, this series delves in depth into Roland''s origins - the perfect introduction to this incredibly realized world; while long-time fans will thrill to adventures merely hinted at in the novels. Be there for the very beginning of a modern classic of fantasy literature! Collects Dark Tower: The Gunslinger Born #1-7.")
                        .isbn("9780785170365")
                        .pages(240)
                        .publishedDate(LocalDate.of(2007, 11, 1))
                        .createdAt(now)
                        .authorName("Stephen King")
                        .genres(List.of("Fantasy", "Graphic Novel", "Speculative Fiction"))
                        .build(),

                BookDTO.builder()
                        .title("The Journey Begins")
                        .description("A BOLD NEW CHAPTER IN STEPHEN KING'S THE DARK TOWER SAGA! Twelve years have passed since the fateful Battle of Jericho Hill and the fall of the gunslingers. Since the Affiliation's resistance against John Farson became little more than a faint memory. Since the friends that stood by young Roland Deschain burned to ash in the Good Man's razing of Gilead. But Roland survived...and now he stalks the desert, hunting the spectral Man in Black in his quest for the Dark Tower. Join Robin Furth, Peter David and Richard Isanove as they welcome superstar artist Sean Phillips (INCOGNITO) into the ka-tet of creators entrusted by Stephen King himself to bring the adult adventures of his most personal creation to life!")
                        .isbn("9780785147091")
                        .pages(136)
                        .publishedDate(LocalDate.of(2011, 2, 1))
                        .createdAt(now)
                        .authorName("Stephen King")
                        .genres(List.of("Horror", "Graphic Novel", "Speculative Fiction"))
                        .build(),

                BookDTO.builder()
                        .title("The Family")
                        .description("Mario Puzo's final masterwork. A sweeping epic saga of corruption, greed, treachery, and sin, The Family is the ultimate crowning achievement of the #1 New York Times bestselling novelist who gave the world The Godfather, arguably the greatest Mafia crime novel ever written.  In The Family, Puzo -- whom the Washington Post calls, “A serious American talent” -- plunges reader into the colorful tumult of the Italian Renaissance, immersing them in the roiling intrigues and deadly affairs of the remarkable family whose name has always been synonymous with power, corruption, poison, and murder: the infamous Borgias.")
                        .isbn("9780061032424")
                        .pages(418)
                        .publishedDate(LocalDate.of(2002, 9, 1))
                        .createdAt(now)
                        .authorName("Mario Puzo")
                        .genres(List.of("General Fiction", "Historical"))
                        .build(),

                BookDTO.builder()
                        .title("Omerta")
                        .description("To Don Raymonde Aprile's children he was a loyal family member, their father's adopted \"nephew.\" To the FBI he was a man who would rather ride his horses than do Mob business. No one knew why Aprile, the last great American Don, had adopted Astorre Viola many years before in Sicily; no one suspected how he had carefully trained him . . . and how, while the Don's children claimed respectable careers in America, Astorre Viola waited for his time to come. \nNow his time has arrived. The Don is dead, his murder one bloody act in a drama of ambition and deceit--from the deadly compromises made by an FBI agent to the greed of two crooked NYPD detectives and the frightening plans of a South American Mob kingpin. In a collision of enemies and lovers, betrayers and loyal soldiers, Astorre Viola will claim his destiny. Because after all these years, this moment is in his blood... .")
                        .isbn("9780345432407")
                        .pages(384)
                        .publishedDate(LocalDate.of(2000, 7, 1))
                        .createdAt(now)
                        .authorName("Mario Puzo")
                        .genres(List.of("General Fiction", "Suspense"))
                        .build()
        );
    }
}

//BookDTO.builder()
//        .title("")
//        .description("")
//        .isbn("")
//        .pages()
//        .publishedDate(LocalDate.of())
//        .createdAt(now)
//        .authorName("")
//        .genres(List.of("", ""))
//        .build();