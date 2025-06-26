CREATE TABLE silver_Artist AS
SELECT 
    ArtistId,
    UPPER(SUBSTR(Name, 1, 1)) || LOWER(SUBSTR(Name, 2)) AS Name
FROM Artist;