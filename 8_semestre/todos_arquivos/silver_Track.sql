CREATE TABLE silver_Track AS
SELECT 
    TrackId,
    Name,
    AlbumId,
    MediaTypeId,
    GenreId,
    Composer,
    Milliseconds,
    Bytes,
    ROUND(UnitPrice, 2) AS UnitPrice,
    CASE 
        WHEN Milliseconds < 180000 THEN 'Short'
        WHEN Milliseconds BETWEEN 180000 AND 300000 THEN 'Medium'
        ELSE 'Long'
    END AS DurationCategory
FROM Track;