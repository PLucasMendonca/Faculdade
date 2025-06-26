CREATE TABLE gold_PerformanceArtistas AS 
SELECT 
    ar.Name AS Artista,
    COUNT(DISTINCT al.AlbumId) AS TotalAlbuns,
    COUNT(DISTINCT t.TrackId) AS TotalMusicas,
    COUNT(il.TrackId) AS TotalMusicasVendidas,
    ROUND(SUM(il.UnitPrice * il.Quantity), 2) AS ReceitaTotal,
    ROUND(AVG(t.Milliseconds) / 60000.0, 2) AS DuracaoMediaMinutos
FROM silver_Artist ar
JOIN Album al ON ar.ArtistId = al.ArtistId
JOIN silver_Track t ON al.AlbumId = t.AlbumId
LEFT JOIN silver_InvoiceLine il ON t.TrackId = il.TrackId
GROUP BY ar.ArtistId
ORDER BY ReceitaTotal DESC;