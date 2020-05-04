package com.wordguess.com;

public class PlaceHolderInfo
{
  private int _iAlpha;
  private int _iTag;

  public PlaceHolderInfo()
  {
  }

  public PlaceHolderInfo(int paramInt1, int paramInt2)
  {
    this._iAlpha = paramInt2;
    this._iTag = paramInt1;
  }

  public int getiAlpha()
  {
    return this._iAlpha;
  }

  public int getiTag()
  {
    return this._iTag;
  }

  public void setInfo(int paramInt1, int paramInt2)
  {
    this._iAlpha = paramInt2;
    this._iTag = paramInt1;
  }
}

/* Location:           C:\Users\Owner\Desktop\test\classes_dex2jar.jar
 * Qualified Name:     com.word.guess.com.PlaceHolderInfo
 * JD-Core Version:    0.6.2
 */