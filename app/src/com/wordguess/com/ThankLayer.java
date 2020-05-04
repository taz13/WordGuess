package com.wordguess.com;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

public class ThankLayer extends CCColorLayer
{
  protected ThankLayer(ccColor4B paramccColor4B)
  {
    super(paramccColor4B);
    CCSprite localCCSprite = CCSprite.sprite("thank_you.png");
    float f1 = getWidth() / localCCSprite.getContentSize().width;
    float f2 = getHeight() / localCCSprite.getContentSize().height;
    localCCSprite.setAnchorPoint(CGPoint.ccp(0.0F, 0.0F));
    localCCSprite.setPosition(CGPoint.ccp(0.0F, 0.0F));
    localCCSprite.setScaleX(f1);
    localCCSprite.setScaleY(f2);
    addChild(localCCSprite);
    CCMenu localCCMenu = CCMenu.menu(new CCMenuItem[] { CCMenuItemImage.item("red_button.png", "red_button.png", this, "onButtonClick") });
    localCCMenu.setPosition(CGPoint.ccp(getWidth() / 2.0F, 150.0F));
    addChild(localCCMenu);
    setIsTouchEnabled(false);
  }

  public void onButtonClick(Object paramObject)
  {
    CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(0.5F, MainLayer.scene()));
  }
}

/* Location:           C:\Users\Owner\Desktop\test\classes_dex2jar.jar
 * Qualified Name:     com.word.guess.com.ThankLayer
 * JD-Core Version:    0.6.2
 */