package se.kth.iv1350.posSystem.view;

import se.kth.iv1350.posSystem.model.ItemDTO;
import se.kth.iv1350.posSystem.model.SaleDTO;
import se.kth.iv1350.posSystem.utilities.Amount;
import se.kth.iv1350.posSystem.utilities.TimeAndDate;

import java.io.PrintWriter;
import java.io.StringWriter;

class OutputFormatter {

    String exceptionMessageFormatter(Exception exception) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("*************************************************\n");
        errorMessage.append("Error: ");
        errorMessage.append(exception.getMessage());
        errorMessage.append("\n*************************************************\n");

        return errorMessage.toString();
    }

    String exceptionLogEntryFormatter(Exception exception) {
        StringBuilder logEntry = new StringBuilder();
        TimeAndDate timeAndDateOfException = new TimeAndDate();
        logEntry.append(timeAndDateOfException.getTimeAndDate());
        logEntry.append("\n");
        StringWriter stackTraceAsString = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stackTraceAsString);
        exception.printStackTrace(printWriter);
        logEntry.append(stackTraceAsString);
        logEntry.append("\n\n");

        return logEntry.toString();
    }


    void startSaleFormatter() {
        System.out.println("\n\n[startSale()]\n");
    }

    void newItemEntryFormatter(SaleDTO saleDTO) {
        ItemDTO lastRegisteredItem = saleDTO.getLastRegisteredItem();
        String itemIDOfLastRegisteredItem = lastRegisteredItem.getItemID();
        Amount qtyOfLastRegisteredItem = saleDTO.getItemsInBasket().get(lastRegisteredItem);

        System.out.println("[addItem('" + itemIDOfLastRegisteredItem + "')]");
        System.out.println(String.format("%.0f", qtyOfLastRegisteredItem.getAmount()) + "* " + lastRegisteredItem);
        System.out.printf("Running total (including VAT): " + saleDTO.getTotalPrice() + "\n\n");
    }

    void endSaleFormatter(SaleDTO saleDTO) {
        System.out.println("[endSale()]");
        System.out.printf("Total Price (including VAT): " + saleDTO.getTotalPrice() + "\n\n");
    }

    void paymentFormatter(SaleDTO saleDTO) {
        System.out.println("[registerPayment(1800)]");
        System.out.println("[printReceipt(saleDTO)]");
        System.out.printf("Change: " + saleDTO.getChange() + " \n\n");
    }
}