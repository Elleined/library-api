CREATE INDEX author_name_idx ON author(name);
CREATE INDEX genre_name_idx ON genre(name);
CREATE INDEX book_title_idx ON book(title);

ALTER TABLE genre ADD FULLTEXT(name);
ALTER TABLE author ADD FULLTEXT(name);
ALTER TABLE book ADD FULLTEXT(title);