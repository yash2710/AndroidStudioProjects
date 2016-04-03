package yash.mp1.itnusip.model;

/**
 * Created by Yash on 04-Apr-15.
 */
public class Contact {
    private String name, id, sip, url, branch;


    public Contact(String name, String id, String sip, String branch) {
        this.name = name;
        this.id = id;
        this.sip = sip;
        this.branch = branch;
    }

    public Contact(String name, String id, String sip, String branch, String url) {
        this.name = name;
        this.id = id;
        this.sip = sip;
        this.url = url;
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public String getId() {
        return id;
    }

    public String getSip() {
        return sip;
    }

    public String getUrl() {
        return url;
    }

    public int getImage() {
        return 0;
    }
}