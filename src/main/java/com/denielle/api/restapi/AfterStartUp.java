package com.denielle.api.restapi;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.dto.BookDTO;
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
                "Sample Genre 1",
                "sample Genre 2",
                "Sorcery",
                "Action Adventure",
                "Epic Fantasy",
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
                "Psychological Suspense",
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
                        .name("Thomas Harris")
                        .biography("William Thomas Harris III is an American writer, best known for a series of suspense novels about his most famous character, Hannibal Lecter.")
                        .createdAt(now)
                        .build(),

                AuthorDTO.builder()
                        .name("Harper Lee")
                        .biography("Nelle Harper Lee was an American novelist. She wrote the 1960 novel To Kill a Mockingbird that won the 1961 Pulitzer Prize and became a classic of modern American literature.")
                        .createdAt(now)
                        .build(),

                AuthorDTO.builder()
                        .name("J.R.R. Tolkien")
                        .biography("John Ronald Reuel Tolkien CBE FRSL was an English writer and philologist. He was the author of the high fantasy works The Hobbit and The Lord of the Rings. From 1925 to 1945, Tolkien was the Rawlinson and Bosworth Professor of Anglo-Saxon and a Fellow of Pembroke College, both at the University of Oxford. ")
                        .createdAt(now)
                        .build(),

                AuthorDTO.builder()
                        .name("J.K. Rowling")
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
                        .build(),

                AuthorDTO.builder()
                        .name("Sample Author 1")
                        .biography("Sample Author Biography 1")
                        .createdAt(now)
                        .build(),

                AuthorDTO.builder()
                        .name("Sample Author 2")
                        .biography("Sample Author Biography 2")
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
                        .build(),

                BookDTO.builder()
                        .title("Red Dragon")
                        .description("In the realm of psychological suspense, Thomas Harris stands alone. Exploring both the nature of human evil and the nerve-racking anatomy of a forensic investigation, Harris unleashes a frightening vision of the dark side of our well-lighted world. In this extraordinary novel, which preceded The Silence of the Lambs and Hannibal, Harris introduced the unforgettable character Dr. Hannibal Lecter. And in it, Will Graham--the FBI man who hunted Lecter down--risks his sanity and his life to duel a killer called the...RED DRAGON")
                        .isbn("9780440206156")
                        .pages(480)
                        .publishedDate(LocalDate.of(1981, 10, 1))
                        .createdAt(now)
                        .authorName("Thomas Harris")
                        .genres(List.of("Thriller", "Suspense"))
                        .build(),

                BookDTO.builder()
                        .title("Cari Mora")
                        .description("Twenty-five million dollars in cartel gold lies hidden beneath a mansion on the Miami Beach waterfront. Ruthless men have tracked it for years. Leading the pack is Hans-Peter Schneider. Driven by unspeakable appetites, he makes a living fleshing out the violent fantasies of other, richer men Cari Mora, caretaker of the house, has escaped from the violence in her native country. She stays in Miami on a wobbly Temporary Protected Status, subject to the iron whim of ICE. She works at many jobs to survive. Beautiful, marked by war, Cari catches the eye of Hans-Peter as he closes in on the treasure. But Cari Mora has surprising skills, and her will to survive has been tested before.\n" + "Monsters lurk in the crevices between male desire and female survival. No other writer in the last century has conjured those monsters with more terrifying brilliance than Thomas Harris. Cari Mora, his sixth novel, is the long-awaited return of an American master.")
                        .isbn("9781538750124")
                        .pages(464)
                        .publishedDate(LocalDate.of(2019, 5, 1))
                        .createdAt(now)
                        .authorName("Thomas Harris")
                        .genres(List.of("Suspense", "Mystery", "Thriller", "Action", "Adventure"))
                        .build(),

                BookDTO.builder()
                        .title("Coyote")
                        .description("COYOTE is based on the real events of the morning I learned of my brother Chase's unexpected death -- a morning when I actually saw and rode with a coyote -- then returned home to learn that Chase was gone. Retold through the eyes of a child, COYOTE is a gentle and lyrical picture book dealing with the subject of loss. It is my hope that COYOTE will help children talk about loss and to show how art can be used to help heal the hurt of losing someone you love. In Chase's memory, my wife Krista and I will donate our share of the proceeds to help the children of Sandy Hook.")
                        .isbn("9780615751276")
                        .pages(32)
                        .publishedDate(LocalDate.of(2013, 1, 1))
                        .createdAt(now)
                        .authorName("Harper Lee")
                        .genres(List.of("General Fiction"))
                        .build(),

                BookDTO.builder()
                        .title("Go Set a Watchman")
                        .description("Maycomb, Alabama. Twenty-six-year-old Jean Louise Finch -- \"Scout\" -- returns home from New York City to visit her aging father, Atticus. Set against the backdrop of the civil rights tensions and political turmoil that were transforming the South, Jean Louise's homecoming turns bittersweet when she learns disturbing truths about her close-knit family, the town, and the people dearest to her. Memories from her childhood flood back, and her values and assumptions are thrown into doubt. Featuring many of the iconic characters from To Kill a Mockingbird, Go Set a Watchman perfectly captures a young woman, and a world, in painful yet necessary transition out of the illusions of the past -- a journey that can only be guided by one's own conscience")
                        .isbn("9780062409874")
                        .pages(288)
                        .publishedDate(LocalDate.of(2015, 7, 1))
                        .createdAt(now)
                        .authorName("Harper Lee")
                        .genres(List.of("Fantasy", "Literary", "Speculative Fiction", "General Fiction"))
                        .build(),

                BookDTO.builder()
                        .title("The Hobbit: Or There and Back Again")
                        .description("Bilbo Baggins was a hobbit who wanted to be left alone in quiet comfort. But the wizard Gandalf came along with a band of homeless dwarves. Soon Bilbo was drawn into their quest, facing evil orcs, savage wolves, giant spiders, and worse unknown dangers. Finally, it was Bilbo--alone and unaided--who had to confront the great dragon Smaug, the terror of an entire countryside!")
                        .isbn("9780345296047")
                        .pages(320)
                        .publishedDate(LocalDate.of(1937, 1, 15))
                        .createdAt(now)
                        .authorName("J.R.R. Tolkien")
                        .genres(List.of("Speculative Fiction", "Epic Fantasy", "Fantasy"))
                        .build(),

                BookDTO.builder()
                        .title("The Nature of Middle-earth")
                        .description("The first ever publication of J.R.R. Tolkien's final writings on Middle-earth, covering a wide range of subjects and perfect for those who have read and enjoyed The Silmarillion, The Lord of the Rings, Unfinished Tales, and The History of Middle-earth, and want to learn more about Tolkien's magnificent world.")
                        .isbn("9780063269606")
                        .pages(464)
                        .publishedDate(LocalDate.of(2021, 9, 1))
                        .createdAt(now)
                        .authorName("J.R.R. Tolkien")
                        .genres(List.of("General Fiction", "Speculative Fiction", "Action Adventure", "Epic Fantasy", "Fantasy"))
                        .build(),

                BookDTO.builder()
                        .title("Fantastic Beasts and Where to Find Them")
                        .description("An approved textbook at Hogwarts School of Witchcraft and Wizardry since publication, Newt Scamander''s masterpiece has entertained wizarding families through the generations. Fantastic Beasts and Where to Find Them is an indispensable introduction to the magical beasts of the Wizarding World. In this comprehensively updated edition, eagle-eyed readers will spot a number of new beasts and an intriguing new author''s note. Scamander''s years of travel and research have created a tome of unparalleled importance. Some of the beasts will be familiar to readers of the Harry Potter books - the Hippogriff, the Basilisk, the Hungarian Horntail ... Others will surprise even the most ardent amateur Magizoologist. Dip in to discover the curious habits of magical beasts across five continents .")
                        .isbn("9780439321600")
                        .pages(64)
                        .publishedDate(LocalDate.of(2001, 6, 1))
                        .createdAt(now)
                        .authorName("J.K. Rowling")
                        .genres(List.of("Speculative Fiction", "Fantasy"))
                        .build(),

                BookDTO.builder()
                        .title("Fantastic Beasts: The Crimes of Grindelwald")
                        .description("At the end of Fantastic Beasts and Where to Find Them, the powerful Dark wizard Gellert Grindelwald was captured in New York with the help of Newt Scamander. But, making good on his threat, Grindelwald escapes custody and sets about gathering followers, most unsuspecting of his true agenda: to raise pure-blood wizards up to rule over all non-magical beings.")
                        .isbn("9781338263893")
                        .pages(304)
                        .publishedDate(LocalDate.of(2018, 11, 1))
                        .createdAt(now)
                        .authorName("J.K. Rowling")
                        .genres(List.of("Speculative Fiction", "Fantasy", "Sorcery"))
                        .build(),

                BookDTO.builder()
                        .title("Baum's American Fairy Tales")
                        .description("This collection of fantasy stories was originally serialized in regional newspapers, prior to being published as a complete volume. The stories, as critics have noted, lack the high-fantasy aspect of the best of Baum's work, in Oz or out. With ironic or nonsensical morals attached to their ends, their tone is more satirical, glib, and tongue-in-cheek than is usual in children's stories; the serialization in newspapers for adult readers was appropriate for the materials.")
                        .isbn("9781499525236")
                        .pages(92)
                        .publishedDate(LocalDate.of(2014, 5, 1))
                        .createdAt(now)
                        .authorName("L. Frank Baum")
                        .genres(List.of("Speculative Fiction", "Fantasy"))
                        .build(),

                BookDTO.builder()
                        .title("King Rinkitink")
                        .description("Rinkitink in Oz is considered by many to be one of L. Frank Baum?s best books, yet Baum did not intend for it to be an Oz book at all. Written as King Rinkitink in 1905, when it saw print in 1916, it was with significant changes. Although the original manuscript is lost, the International Wizard of Oz Club has brought King Rinkitink back to life with a new ending in Baum's style and sensibility. When sea-raiders invade the peaceful island of Pingaree and carry off its inhabitants to slavery, only young Prince Inga, jolly King Rinkitink, and his grumpy goat Bilbil are left behind. Aided by three Magic Pearls, these unlikely heroes set out to rescue Inga?s people from captivity, a quest that takes them across the ocean and into the dangerous underground world of the Nome King! One of Baum?s best stories just got better with five new chapters, numerous b&w illustrations by John R. Neill and Javi Laparra, and color plates reproduced here in b&w.")
                        .isbn("9780991199075")
                        .pages(356)
                        .publishedDate(LocalDate.of(2017, 12, 1))
                        .createdAt(now)
                        .authorName("L. Frank Baum")
                        .genres(List.of("Speculative Fiction", "Fantasy"))
                        .build(),

                BookDTO.builder()
                        .title("Before Scarlett")
                        .description("Discovered one sultry summer in an Atlanta basement full of sixty years' worth of accumulated debris, the writings of a young Margaret Mitchell reveal a prodigious and inspirational talent for such a young girl. The writer, who would later pen the best-selling book of all time after the Bible (and one that still sells more than 200,000 copies annually), was a precocious, imaginative, headstrong rebel and yet as distracted by everyday concerns about parental approval and social insecurities as any child. Nevertheless, as shown in the pages of Before Scarlett, Mitchell displayed an amazing talent through her writing of letters, journals, short stories, and one-act plays (later staged in her midtown Atlanta home). ")
                        .isbn("9781570039386")
                        .pages(240)
                        .publishedDate(LocalDate.of(2010, 8, 1))
                        .createdAt(now)
                        .authorName("Margaret Mitchell")
                        .genres(List.of("General Fiction"))
                        .build(),

                BookDTO.builder()
                        .title("Lost Laysen")
                        .description("A long-lost novel by the author of Gone with the Wind provides richly romantic saga of a stormy love triangle and characters torn between passion and honor, whose lives are forever altered by a terrible catastrophe. 350,000 first printing.")
                        .isbn("9780684824284")
                        .pages(128)
                        .publishedDate(LocalDate.of(1996, 5, 1))
                        .createdAt(now)
                        .authorName("Margaret Mitchell")
                        .genres(List.of("General Fiction"))
                        .build(),

                BookDTO.builder()
                        .title("The Temple of Gold")
                        .description("Acclaimed for such Academy Award -- winning screenplays as Butch Cassidy and the Sundance Kid and such thrillers as Marathon Man, not to mention the bestselling classic The Princess Bride, William Goldman stands as one of the most beloved writers in America. But long before these triumphs, he caused a sensation with his brilliant first novel, a powerful story of reckless youth that was hailed as a worthy rival to The Catcher in the Rye.")
                        .isbn("9780345439741")
                        .pages(224)
                        .publishedDate(LocalDate.of(2001, 10, 1))
                        .createdAt(now)
                        .authorName("William Goldman")
                        .genres(List.of("Suspense"))
                        .build(),

                BookDTO.builder()
                        .title("Magic")
                        .description("One of those can't-put-it-down-until-the-last-page-is-turned monsters that has readers all over the country missing sleep. Minneapolis Tribune Corky is a brilliant entertainer with a bright future ahead of him. He has good looks, many women, and enormous talent. He also had a secret and a certainty: a secret that must be hidden from his public at all costs; and a certainty that the dark forces of magic were out to destroy him. Fascinating . . . This dazzling psychological thriller cannot be put down! . . . The most imaginative and enjoyable novel I've read since Marathon Man. . . . [A] bizarre journey into the world of illusion. St. Louis Post-Dispatch Kept me up half the night. . . . A brilliantly alarming novel! Cosmopolitan")
                        .isbn("9780307487865")
                        .pages(243)
                        .publishedDate(LocalDate.of(2009, 1, 1))
                        .createdAt(now)
                        .authorName("William Goldman")
                        .genres(List.of("Suspense", "Psychological Suspense"))
                        .build()
        );
    }
}

/*
 * FictionDB.com. (n.d.). FictionDB API. Retrieved May 25, 2023, from https://www.fictiondb.com/
 * You can use this template to create your own book record
 * BookDTO.builder()
 *        .title("")
 *        .description("")
 *        .isbn("")
 *        .pages()
 *        .publishedDate(LocalDate.of())
 *        .createdAt(now)
 *        .authorName("")
 *        .genres(List.of("", ""))
 *        .build();
 */