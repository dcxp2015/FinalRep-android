package com.dcxp.tone.uploadexplorer;

/**
 * Created by Daniel on 7/17/2015.
 */
public class UploadedPhrase {
    private String name;
    private String submitter;

    public UploadedPhrase(String name, String submitter) {
        this.name = name;
        this.submitter = submitter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getName() {
        return name;
    }

    public String getSubmitter() {
        return submitter;
    }
}
