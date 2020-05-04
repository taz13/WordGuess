package com.wordguess.com;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor4B;

public class MsgLayer extends CCColorLayer
{
  private static float f1 = 50.0F;
  private static float f2 = 30.0F;
  private static float py1 = 150.0F;
  private static float py2 = 200.0F;
  private static float py3 = 200.0F;
  private CCLabel _lblCmt1;
  private CCLabel _lblCmt2;
  private CCLabel _lblCmt3;
  private CCLabel _lblCmt4;
  private CCLabel _lblCoinLack;
  private CCLabel _lblPlayAgain;
  private CCLabel _lblRight;
  private CCLabel _lblWrong;
  private CCMenu _menuCoinLack;
  private CCMenu _menuPlayAgain;
  private CCMenu _menuRight;
  private CCMenu _menuWrong;
  private PlayLayer _plLayer;

  protected MsgLayer(ccColor4B paramccColor4B)
  {
    super(paramccColor4B);
    setOpacity(150);
    this._lblRight = CCLabel.makeLabel("WONDERFUL!", "ROCB.TTF", f1);
    this._lblRight.setPosition(CGPoint.ccp(getWidth() / 2.0F, getHeight() - py1));
    addChild(this._lblRight);
    this._lblCmt1 = CCLabel.makeLabel("Your guess is right.", "ROCB.TTF", f2);
    this._lblCmt1.setPosition(CGPoint.ccp(getWidth() / 2.0F, getHeight() - py2));
    addChild(this._lblCmt1);
    this._menuRight = CCMenu.menu(new CCMenuItem[] { CCMenuItemImage.item("btn_next.png", "btn_next.png", this, "onContinue") });
    this._menuRight.setPosition(CGPoint.ccp(getWidth() / 2.0F, py3));
    addChild(this._menuRight);
    this._lblWrong = CCLabel.makeLabel("SORRY!", "ROCB.TTF", f1);
    this._lblWrong.setPosition(CGPoint.ccp(getWidth() / 2.0F, getHeight() - py1));
    addChild(this._lblWrong);
    this._lblCmt2 = CCLabel.makeLabel("Your guess is wrong.", "ROCB.TTF", f2);
    this._lblCmt2.setPosition(CGPoint.ccp(getWidth() / 2.0F, getHeight() - py2));
    addChild(this._lblCmt2);
    this._menuWrong = CCMenu.menu(new CCMenuItem[] { CCMenuItemImage.item("btn_check.png", "btn_check.png", this, "onTryAgain") });
    this._menuWrong.setPosition(CGPoint.ccp(getWidth() / 2.0F, py3));
    addChild(this._menuWrong);
    this._lblPlayAgain = CCLabel.makeLabel("END OF GAME", "ROCB.TTF", f1);
    this._lblPlayAgain.setPosition(CGPoint.ccp(getWidth() / 2.0F, getHeight() - py1));
    addChild(this._lblPlayAgain);
    this._lblCmt3 = CCLabel.makeLabel("Do you want to play again?", "ROCB.TTF", f2);
    this._lblCmt3.setPosition(CGPoint.ccp(getWidth() / 2.0F, getHeight() - py2));
    addChild(this._lblCmt3);
    this._menuPlayAgain = CCMenu.menu(new CCMenuItem[] { CCMenuItemImage.item("btn_play.png", "btn_play.png", this, "onPlayAgain") });
    this._menuPlayAgain.setPosition(CGPoint.ccp(getWidth() / 2.0F, py3));
    addChild(this._menuPlayAgain);
    this._lblCoinLack = CCLabel.makeLabel("LACK OF COINS!", "ROCB.TTF", f1);
    this._lblCoinLack.setPosition(CGPoint.ccp(getWidth() / 2.0F, getHeight() - 100.0F));
    addChild(this._lblCoinLack);
    this._lblCmt4 = CCLabel.makeLabel("You must have 50 Coins or more.", "ROCB.TTF", f2);
    this._lblCmt4.setPosition(CGPoint.ccp(getWidth() / 2.0F, getHeight() - 160.0F));
    addChild(this._lblCmt4);
    this._menuCoinLack = CCMenu.menu(new CCMenuItem[] { CCMenuItemImage.item("btn_check.png", "btn_check.png", this, "onCoinLack") });
    this._menuCoinLack.setPosition(CGPoint.ccp(getWidth() / 2.0F, 150.0F));
    addChild(this._menuCoinLack);
    setPosition(CGPoint.ccp(getWidth(), 0.0F));
    setIsTouchEnabled(false);
  }

  public void onCoinLack(Object paramObject)
  {
    this._lblCoinLack.setVisible(false);
    this._lblCmt4.setVisible(false);
    this._menuCoinLack.setVisible(false);
    setPosition(CGPoint.ccp(getWidth(), 0.0F));
    setVisible(false);
  }

  public void onContinue(Object paramObject)
  {
    this._lblRight.setVisible(false);
    this._lblCmt1.setVisible(false);
    this._menuRight.setVisible(false);
    setPosition(CGPoint.ccp(getWidth(), 0.0F));
    setVisible(false);
    this._plLayer.onContinue();
  }

  public void onPlayAgain(Object paramObject)
  {
    this._lblPlayAgain.setVisible(false);
    this._lblCmt3.setVisible(false);
    this._menuPlayAgain.setVisible(false);
    setPosition(CGPoint.ccp(getWidth(), 0.0F));
    setVisible(false);
    this._plLayer.onPlayAgain();
  }

  public void onTryAgain(Object paramObject)
  {
    this._lblWrong.setVisible(false);
    this._lblCmt2.setVisible(false);
    this._menuWrong.setVisible(false);
    setPosition(CGPoint.ccp(getWidth(), 0.0F));
    setVisible(false);
    this._plLayer.onTryAgain();
  }

  public void setParentLayer(PlayLayer paramPlayLayer)
  {
    this._plLayer = paramPlayLayer;
  }

  public void showMsg(String paramString)
  {
    
    while (true)
    {
      setVisible(true);
      if (paramString.equals("right"))
      {
        this._lblRight.setVisible(true);
        this._lblCmt1.setVisible(true);
        this._menuRight.setVisible(true);
        this._lblWrong.setVisible(false);
        this._lblCmt2.setVisible(false);
        this._menuWrong.setVisible(false);
        this._lblPlayAgain.setVisible(false);
        this._lblCmt3.setVisible(false);
        this._menuPlayAgain.setVisible(false);
        this._lblCoinLack.setVisible(false);
        this._lblCmt4.setVisible(false);
        this._menuCoinLack.setVisible(false);
        return;
      }
      
      else if (paramString.equals("wrong"))
      {
        this._lblRight.setVisible(false);
        this._lblCmt1.setVisible(false);
        this._menuRight.setVisible(false);
        this._lblWrong.setVisible(true);
        this._lblCmt2.setVisible(true);
        this._menuWrong.setVisible(true);
        this._lblPlayAgain.setVisible(false);
        this._lblCmt3.setVisible(false);
        this._menuPlayAgain.setVisible(false);
        this._lblCoinLack.setVisible(false);
        this._lblCmt4.setVisible(false);
        this._menuCoinLack.setVisible(false);
        return;
      }
      else if (paramString.equals("playgameagain"))
      {
        this._lblRight.setVisible(false);
        this._lblCmt1.setVisible(false);
        this._menuRight.setVisible(false);
        this._lblWrong.setVisible(false);
        this._lblCmt2.setVisible(false);
        this._menuWrong.setVisible(false);
        this._lblPlayAgain.setVisible(true);
        this._lblCmt3.setVisible(true);
        this._menuPlayAgain.setVisible(true);
        this._lblCoinLack.setVisible(false);
        this._lblCmt4.setVisible(false);
        this._menuCoinLack.setVisible(false);
        return;
      }
      else if(paramString.equals("coinlack"))
      {
        this._lblRight.setVisible(false);
        this._lblCmt1.setVisible(false);
        this._menuRight.setVisible(false);
        this._lblWrong.setVisible(false);
        this._lblCmt2.setVisible(false);
        this._menuWrong.setVisible(false);
        this._lblPlayAgain.setVisible(false);
        this._lblCmt3.setVisible(false);
        this._menuPlayAgain.setVisible(false);
        this._lblCoinLack.setVisible(true);
        this._lblCmt4.setVisible(true);
        this._menuCoinLack.setVisible(true);
        return ;
      }
    }
    
  }
}

/* Location:           C:\Users\Owner\Desktop\test\classes_dex2jar.jar
 * Qualified Name:     com.word.guess.com.MsgLayer
 * JD-Core Version:    0.6.2
 */