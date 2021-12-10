//The SequenceHandler is the piece of code that defines the sequence of events
//that constitute the experiment.
//
//SequenceHandler.Next() will run the next step in the sequence.
//
//We can also switch between the main sequence of events and a subsequence
//using the SequenceHandler.SetLoop command. This takes two inputs:
//The first sets which loop we are in. 0 is the main loop. 1 is the first
//subloop. 2 is the second subloop, and so on.
//
//The second input is a Boolean. If this is set to true we initialise the 
//position so that the sequence will start from the beginning. If it is
//set to false, we will continue from whichever position we were currently in.
//
//So SequenceHandler.SetLoop(1,true) will switch to the first subloop,
//starting from the beginning.
//
//SequenceHandler.SetLoop(0,false) will switch to the main loop,
//continuing from where we left off.

//TODO:
//scroll
//data output
//resume where you left off

package com.sam.webtasks.client;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sam.webtasks.basictools.CheckIdExists;
import com.sam.webtasks.basictools.CheckScreenSize;
import com.sam.webtasks.basictools.ClickPage;
import com.sam.webtasks.basictools.Consent;
import com.sam.webtasks.basictools.Counterbalance;
import com.sam.webtasks.basictools.InfoSheet;
import com.sam.webtasks.basictools.Initialise;
import com.sam.webtasks.basictools.Names;
import com.sam.webtasks.basictools.PHP;
import com.sam.webtasks.basictools.ProgressBar;
import com.sam.webtasks.basictools.Slider;
import com.sam.webtasks.basictools.TimeStamp;
import com.sam.webtasks.iotask1.IOtask1Block;
import com.sam.webtasks.iotask1.IOtask1BlockContext;
import com.sam.webtasks.iotask1.IOtask1InitialiseTrial;
import com.sam.webtasks.iotask1.IOtask1RunTrial;
import com.sam.webtasks.iotask2.IOtask2Block;
import com.sam.webtasks.iotask2.IOtask2BlockContext;
import com.sam.webtasks.iotask2.IOtask2RunTrial;
import com.sam.webtasks.perceptualTask.PerceptBlock;
import com.sam.webtasks.timeBasedOffloading.TimeBlock;
import com.sam.webtasks.iotask2.IOtask2InitialiseTrial;
import com.sam.webtasks.iotask2.IOtask2PreTrial;

public class SequenceHandler {
	static int totalCircles, nTargets, reminderLockout;
	
	public static void Next() {	
		//Window.alert(whichLoop + "," + sequencePosition.get(whichLoop));
		
		// move forward one step in whichever loop we are now in
		sequencePosition.set(whichLoop, sequencePosition.get(whichLoop) + 1);

		switch (whichLoop) {
		case 0: // MAIN LOOP
			switch (sequencePosition.get(0)) {
			/***********************************************************************
			 * The code here defines the main sequence of events in the experiment *
			 **********************************************************************/
			case 1:
				ClickPage.Run(Instructions.Get(0), "Next");
				break;
			case 2:
				IOtask2Block block0 = new IOtask2Block();
				block0.totalCircles = 7;
				block0.blockNum = -1;
				block0.nTargets = 0;
				block0.Run();
				break;
			case 3:
				ClickPage.Run(Instructions.Get(1),  "Next");
				break;
			case 4:
				IOtask2Block block1 = new IOtask2Block();
				block1.totalCircles = 7;
				block1.blockNum = -2;
				block1.nTargets = 1;
				block1.Run();
				break;
			case 5:
				if (IOtask2BlockContext.getnHits() == 0) { 
					SequenceHandler.SetPosition(SequenceHandler.GetPosition()-2); //this line means that instead of moving forward we will repeat the previous instructions
					ClickPage.Run(Instructions.Get(2), "Try again");
				} else {
					SequenceHandler.Next(); //move to the next instruction
				}
				break;
			case 6:
				ClickPage.Run(Instructions.Get(3), "Next");
				break;
			case 7:
				IOtask2Block block2 = new IOtask2Block();
				block2.totalCircles = 9;
				block2.blockNum = -3;
				block2.nTargets = 3;
				block2.Run();
				break;
			case 8:
				ClickPage.Run(Instructions.Get(4), "Next");
				break;
			case 9:
				IOtask2Block block3 = new IOtask2Block();
				block3.totalCircles = 11;
				block3.blockNum = -4;
				block3.nTargets = 5;
				block3.Run();
				break;
			case 10:
				ClickPage.Run(Instructions.Get(5),  "Next");
				break;
			case 11:
				IOtask2Block block4 = new IOtask2Block();
				block4.totalCircles = 13;
				block4.blockNum = -5;
				block4.nTargets = 7;
				block4.Run();
				break;
			case 12:
				Slider.Run(Instructions.Get(6), "0%", "100%");
				break;
			case 13:
				PHP.logData("jol1", ""+Slider.getSliderValue(), true);
				break;
			case 14:
				Slider.Run(Instructions.Get(7), "0%", "100%");
				break;
			case 15:
				PHP.logData("jol3", ""+Slider.getSliderValue(), true);
				break;
			case 16:
				Slider.Run(Instructions.Get(8), "0%", "100%");
				break;
			case 17:
				PHP.logData("jol5", ""+Slider.getSliderValue(), true);
				break;
			case 18:
				Slider.Run(Instructions.Get(9), "0%", "100%");
				break;
			case 19:
				PHP.logData("jol7", ""+Slider.getSliderValue(), true);
				break;		
			case 20:
				ClickPage.Run(Instructions.Get(10), "Next");
				break;
			case 21:
				IOtask2Block block5 = new IOtask2Block();
				block5.totalCircles = 13;
				block5.blockNum = -6;
				block5.nTargets = 7;
				block5.offloadCondition=Names.REMINDERS_MANDATORY_TARGETONLY;
				block5.Run();
				break;
			case 22:
				if (IOtask2BlockContext.getnHits() < 6) { 
					SequenceHandler.SetPosition(SequenceHandler.GetPosition()-2); //this line means that instead of moving forward we will repeat the previous instructions
					ClickPage.Run(Instructions.Get(11), "Try again");
				} else {
					SequenceHandler.Next(); //move to the next instruction
				}
				break;
			case 23:
				ClickPage.Run(Instructions.Get(12),  "Next");
				break;
			case 24:
				//add progress bar to screen
				ProgressBar.Initialise();
				ProgressBar.Show();
				ProgressBar.SetProgress(0,  48);
				Params.progress=0;
				
				IOtask2Block block6 = new IOtask2Block();
				block6.blockNum = 1;
				block6.WMC = true;
				block6.nTargetsVariable = true;
				block6.nTargetsShuffle = true;
				block6.updateProgress = true;
				block6.offloadCondition=Names.REMINDERS_NOTALLOWED;
				
				for (int i=0; i<4; i++) {
					block6.nTargetsList.add(1);
					block6.nTargetsList.add(3);
					block6.nTargetsList.add(5);
					block6.nTargetsList.add(7);
				}
				
				block6.nTrials = 16;

				
				block6.Run();	
				break;
			case 25:
				ClickPage.Run(Instructions.Get(13),  "Next");
				break;
			case 26:
				IOtask2Block block7 = new IOtask2Block();
				block7.blockNum = 2;
				block7.WMC = true;
				block7.nTargetsVariable = true;
				block7.nTargetsShuffle = true;
				block7.updateProgress = true;
				block7.offloadCondition=Names.REMINDERS_MANDATORY_TARGETONLY;
				
				for (int i=0; i<4; i++) {
					block7.nTargetsList.add(1);
					block7.nTargetsList.add(3);
					block7.nTargetsList.add(5);
					block7.nTargetsList.add(7);
				}
				
				block7.nTrials = 16;

				
				block7.Run();	
				break;
			case 27:
				ClickPage.Run(Instructions.Get(14),  "Next");
				break;
			case 28:
				IOtask2Block block8 = new IOtask2Block();
				block8.blockNum = 3;
				block8.WMC = true;
				block8.nTargetsVariable = true;
				block8.nTargetsShuffle = true;
				block8.updateProgress = true;
				
				for (int i=0; i<4; i++) {
					block8.nTargetsList.add(1);
					block8.nTargetsList.add(3);
					block8.nTargetsList.add(5);
					block8.nTargetsList.add(7);
				}
				
				block8.nTrials = 16;

				
				block8.Run();	
				break;
			case 29:
				ProgressBar.Hide();
				
				ClickPage.Run(Instructions.Get(15),  "nobutton");
				break;
			}
			break;

		/********************************************/
		/* no need to edit the code below this line */
		/********************************************/

		case 1: // initialisation loop
			switch (sequencePosition.get(1)) {
			case 1:
				// initialise experiment settings
				Initialise.Run();
				break;
			case 2:
				// make sure that a participant ID has been registered.
				// If not, the participant may not have accepted the HIT
				CheckIdExists.Run();
				break;
			case 3:
				// check the status of this participant ID.
				// have they already accessed or completed the experiment? if so,
				// we may want to block them, depending on the setting of
				// SessionInfo.eligibility
				PHP.CheckStatus();
				break;
			case 4:
				// check whether this participant ID has been used to access a previous experiment
				PHP.CheckStatusPrevExp();
				break;
			case 5:
				// clear screen, now that initial checks have been done
				RootPanel.get().clear();

				// make sure the browser window is big enough
				CheckScreenSize.Run(SessionInfo.minScreenSize, SessionInfo.minScreenSize);
				break;
			case 6:
				if (SessionInfo.runInfoConsentPages) { 
					InfoSheet.Run(Instructions.InfoText());
				} else {
					SequenceHandler.Next();
				}
				break;
			case 7:
				if (SessionInfo.runInfoConsentPages) { 
					Consent.Run();
				} else {
					SequenceHandler.Next();
				}
				break;
			case 8:
				//record the participant's counterbalancing condition in the status table				
				if (!SessionInfo.resume) {
					PHP.UpdateStatus("" + Counterbalance.getCounterbalancingCell() + ",1,0,0,0,0");
				} else {
					SequenceHandler.Next();
				}
				break;
			case 9:
				SequenceHandler.SetLoop(0, true); // switch to and initialise the main loop
				SequenceHandler.Next(); // start the loop
				break;
			}
			break;
		case 2: // IOtask1 loop
			switch (sequencePosition.get(2)) {
			/*************************************************************
			 * The code here defines the sequence of events in subloop 2 *
			 * This runs a single trial of IOtask1                       *
			 *************************************************************/
			case 1:
				// first check if the block has ended. If so return control to the main sequence
				// handler
				IOtask1Block block = IOtask1BlockContext.getContext();

				if (block.currentTrial == block.nTrials) {
					SequenceHandler.SetLoop(0, false);
				}

				SequenceHandler.Next();
				break;
			case 2:
				// now initialise trial and present instructions
				IOtask1InitialiseTrial.Run();
				break;
			case 3:
				// now run the trial
				IOtask1RunTrial.Run();
				break;
			case 4:
				// we have reached the end, so we need to restart the loop
				SequenceHandler.SetLoop(2, true);
				SequenceHandler.Next();
				break;
			}
			break;
		case 3: //IOtask2 loop
			switch (sequencePosition.get(3)) {
			/*************************************************************
			 * The code here defines the sequence of events in subloop 3 *
			 * This runs a single trial of IOtask2                       *
			 *************************************************************/
			case 1:
				// first check if the block has ended. If so return control to the main sequence
				// handler
				IOtask2Block block = IOtask2BlockContext.getContext();
				
				if (block.currentTrial == block.nTrials) {
					SequenceHandler.SetLoop(0,  false);
				}
				
				SequenceHandler.Next();
				break;
			case 2:
				IOtask2InitialiseTrial.Run();
				break;
			case 3:;
				//present the pre-trial choice if appropriate
				if (IOtask2BlockContext.currentTargetValue() > -1) {
					IOtask2PreTrial.Run();
				} else { //otherwise just skip to the start of the block
					if ((IOtask2BlockContext.getTrialNum() > 0)&&(IOtask2BlockContext.countdownTimer())) {
						//if we're past the first trial and there's a timer, click to begin
						ClickPage.Run("Ready?", "Continue");
					} else if (IOtask2BlockContext.WMC()) {
						String s = "";
						
						if (IOtask2BlockContext.getNtargets()>1) {
							s = "s";
						}
						
						String reminderString = "It is up to you whether to set reminders or remember them with your own memory.";
						
						if (IOtask2BlockContext.getOffloadCondition() == Names.REMINDERS_MANDATORY_TARGETONLY) {
							reminderString = "You must set a reminder for each special circle.";
						}
						
						if (IOtask2BlockContext.getOffloadCondition() == Names.REMINDERS_NOTALLOWED) {
							reminderString = "You will not be able to set reminders, so you have to use your own memory.";
						}
						
						ClickPage.Run("This time you will get " + IOtask2BlockContext.getNtargets() + " special circle" + s + ".<br><br>"
								+ reminderString, "Continue");					
					} else	{
						SequenceHandler.Next();
					}
				}
				break;
			case 4:
				if (IOtask2BlockContext.getNTrials() == -1) { //if nTrials has been set to -1, we quit before running
					SequenceHandler.SetLoop(0,  false);
					SequenceHandler.Next();
				} else {
					//otherwise, run the trial
					IOtask2RunTrial.Run();
				}
				break;
			case 5:
				IOtask2PostTrial.Run();
				break;
			case 6:
				//we have reached the end, so we need to restart the loop
				SequenceHandler.SetLoop(3,  true);
				SequenceHandler.Next();
				break;
			}
		}
	}
	
	private static ArrayList<Integer> sequencePosition = new ArrayList<Integer>();
	private static int whichLoop;

	public static void SetLoop(int loop, Boolean init) {
		whichLoop = loop;

		while (whichLoop + 1 > sequencePosition.size()) { // is this a new loop?
			// if so, initialise the position in this loop to zero
			sequencePosition.add(0);
		}

		if (init) { // go the beginning of the sequence if init is true
			sequencePosition.set(whichLoop, 0);
		}
	}
	
	// get current loop
	public static int GetLoop() {
		return (whichLoop);
	}

	// set a new position
	public static void SetPosition(int newPosition) {
		sequencePosition.set(whichLoop, newPosition);
	}

	// get current position
	public static int GetPosition() {
		return (sequencePosition.get(whichLoop));
	}
	
	// get current position from particular loop
	public static int GetPosition(int nLoop) {
		return (sequencePosition.get(nLoop));
	}
}
