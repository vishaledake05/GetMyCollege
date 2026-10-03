package com.s1.view;

public class nextpage {
    public String collegeName;
    public String branch;
    public double cutoff;
    public String caste;

    public nextpage(String collegeName, String branch, double cutoff, String caste) {
        this.collegeName = collegeName;
        this.branch = branch;
        this.cutoff = cutoff;
        this.caste = caste;
    }

    @Override
    public String toString() {
        return "College: " + collegeName + "\nBranch: " + branch +
               "\nCutoff: " + cutoff + "\nCaste: " + caste;
    }
}
