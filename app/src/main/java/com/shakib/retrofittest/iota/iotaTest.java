package com.shakib.retrofittest.iota;

import com.google.gson.annotations.SerializedName;

public class iotaTest {

    @SerializedName("Group A")
    private String GroupA;

    @SerializedName("Group B")
    private String GroupB;

    @SerializedName("Group C")
    private String GroupC;

    @SerializedName("Group D")
    private String GroupD;

    @SerializedName("Group E")
    private String GroupE;

    @SerializedName("Group F")
    private String GroupF;

    @SerializedName("Group G")
    private String GroupG;

    public iotaTest(String groupA, String groupB, String groupC, String groupD, String groupE, String groupF, String groupG) {
        GroupA = groupA;
        GroupB = groupB;
        GroupC = groupC;
        GroupD = groupD;
        GroupE = groupE;
        GroupF = groupF;
        GroupG = groupG;
    }

    public String getGroupA() {
        return GroupA;
    }

    public String getGroupB() {
        return GroupB;
    }

    public String getGroupC() {
        return GroupC;
    }

    public String getGroupD() {
        return GroupD;
    }

    public String getGroupE() {
        return GroupE;
    }

    public String getGroupF() {
        return GroupF;
    }

    public String getGroupG() {
        return GroupG;
    }
}
