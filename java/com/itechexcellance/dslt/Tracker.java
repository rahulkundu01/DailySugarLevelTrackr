package com.itechexcellance.dslt;

public class Tracker {
    int _id;
    String _date;
    String _fasting;
    String _postb;
    String _postl;

    public Tracker(String Date,String Fasting, String PostB, String PostL)
    {

        this._date=Date;
        this._fasting=Fasting;
        this._postb=PostB;
        this._postl=PostL;
    }


    public int getID()
    {
        return this._id;
    }
    public void SetID(int ID)
    {
        this._id=ID;
    }

    public String getDate()
    {
        return this._date;
    }

    public String getFasting()
    {
        return this._fasting;
    }
    public String getPostB()
    {
        return this._postb;
    }
    public String getPostL()
    {
        return this._postl;
    }

    public void setDate(String Date)
    {
        this._date=Date;
    }
    public void setFasting(String Fasting)
    {
        this._fasting=Fasting;
    }
    public void setPostB(String PostB)
    {
        this._postb=PostB;
    }
    public void setPostL(String PostL)
    {
        this._postl=PostL;
    }

}
