package com.wordguess.com;

import org.cocos2d.types.CGPoint;

public class PicInfo
{
  private CGPoint _anchor;
  private boolean _bZoomed;
  private CGPoint _pos;
  private float _sx;

  public PicInfo(boolean paramBoolean, CGPoint paramCGPoint1, CGPoint paramCGPoint2, float paramFloat)
  {
    this._bZoomed = paramBoolean;
    this._anchor = paramCGPoint1;
    this._pos = paramCGPoint2;
    this._sx = paramFloat;
  }

  public CGPoint getAnchor()
  {
    return this._anchor;
  }

  public CGPoint getPos()
  {
    return this._pos;
  }

  public float getScale()
  {
    return this._sx;
  }

  public boolean isZoomed()
  {
    return this._bZoomed;
  }

  public void setZoomed(boolean paramBoolean)
  {
    this._bZoomed = paramBoolean;
  }
}

/* Location:           C:\Users\Owner\Desktop\test\classes_dex2jar.jar
 * Qualified Name:     com.word.guess.com.PicInfo
 * JD-Core Version:    0.6.2
 */