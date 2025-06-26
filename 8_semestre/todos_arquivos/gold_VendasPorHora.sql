CREATE TABLE gold_VendasPorHora AS
SELECT 
    STRFTIME('%H', InvoiceDate) AS Hora,
    COUNT(*) AS TotalFaturas,
    ROUND(SUM(Total), 2) AS ValorTotalVendas,
    ROUND(AVG(Total), 2) AS ValorMedioPorVenda
FROM silver_Invoice
GROUP BY Hora
ORDER BY Hora;