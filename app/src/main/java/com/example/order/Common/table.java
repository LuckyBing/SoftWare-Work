package com.example.order.Common;

public class table {
    private String tableId;
    private String tableNum;

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableId() {
        return tableId;
    }

    public String getTableNum() {
        return tableNum;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }
    public table(String tableId,String tableNum)
    {
        this.tableId=tableId;
        this.tableNum=tableNum;
    }
}
