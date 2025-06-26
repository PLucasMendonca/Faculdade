CREATE TABLE gold_VendasPorPais AS
SELECT 
    BillingCountry AS Country,
    COUNT(*) AS TotalInvoices,
    ROUND(SUM(Total), 2) AS TotalSales,
    ROUND(AVG(Total), 2) AS AvgSaleValue,
    MIN(InvoiceDate) AS FirstSaleDate,
    MAX(InvoiceDate) AS LastSaleDate
FROM silver_Invoice
GROUP BY BillingCountry
ORDER BY TotalSales DESC;
