package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, fPressed;
	public GamePanel gp;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();

		//title state
		if(gp.GAME_STATE == gp.TITLE_STATE){
			switch(code) {
				case KeyEvent.VK_W:
					gp.ui.command_number--;
					if(gp.ui.command_number < 0) gp.ui.command_number = 2;
					break;
				case KeyEvent.VK_S:
					gp.ui.command_number++;
					if(gp.ui.command_number > 2) gp.ui.command_number = 0;
					break;
				case  KeyEvent.VK_ENTER:
					if(gp.ui.command_number == 0){
						gp.GAME_STATE = gp.PLAY_STATE;
						//play music here
					}
					if(gp.ui.command_number == 2){
						System.exit(0);
					}
			}
		}

		if(gp.GAME_STATE == gp.PLAY_STATE || gp.GAME_STATE == gp.PAUSE_STATE ){
			switch(code) {
				case KeyEvent.VK_W:
					upPressed = true;
					break;

				case KeyEvent.VK_S:
					downPressed = true;
					break;

				case KeyEvent.VK_A:
					leftPressed = true;
					break;

				case KeyEvent.VK_D:
					rightPressed = true;
					break;
				case KeyEvent.VK_F:
					fPressed = true;
					break;
				case KeyEvent.VK_SPACE:
					spacePressed = true;
					break;
				case KeyEvent.VK_P:
					if(gp.GAME_STATE == gp.PLAY_STATE){
						gp.GAME_STATE = gp.PAUSE_STATE;
					}else {
						gp.GAME_STATE = gp.PLAY_STATE;
					}
					break;

			}
		}



	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		switch(code) {
			case KeyEvent.VK_W:
				upPressed = false;
				break;
				
			case KeyEvent.VK_S:
				downPressed = false;
				break;

			case KeyEvent.VK_A:
				leftPressed = false;
				break;
				
			case KeyEvent.VK_D:
				rightPressed = false;
				break;
			case KeyEvent.VK_F:
				fPressed = false;
				break;
			case KeyEvent.VK_SPACE:
				spacePressed = false;
				break;


		}
		
	}

}
