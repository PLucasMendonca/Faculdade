CREATE TABLE gold_MusicasMaisVendidas AS
SELECT 
    t.Name AS NomeMusica,
    t.Milliseconds AS DuracaoMs,
    ROUND(t.UnitPrice, 2) AS PrecoUnitario,
    ar.Name AS Artista,
    al.Title AS Album,
    COUNT(il.InvoiceLineId) AS NumeroVendas,
    SUM(il.Quantity) AS TotalUnidadesVendidas,
    ROUND(SUM(il.UnitPrice * il.Quantity), 2) AS ReceitaTotal
FROM silver_Track t
JOIN silver_InvoiceLine il ON t.TrackId = il.TrackId
JOIN silver_Album al ON t.AlbumId = al.AlbumId
JOIN silver_Artist ar ON al.ArtistId = ar.ArtistId
GROUP BY t.TrackId
ORDER BY NumeroVendas DESC;