package org.firstinspires.ftc.teamcode.AutonsPlan.Level1Opmodes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BP;
@Disabled
@Autonomous(name = "Level1FoundationBlue", group = "Linear OpMode")
public class Level1FoundationBlue extends LinearOpMode {
    public BP bp = new BP();

    @Override
    public void runOpMode() throws InterruptedException {
        bp.HW.initHW(this, true);
        waitForStart();
        //Lv1:
        bp.curveClaw(0);
        sleep(1000);
        //Move sideways 9 inches
        bp.encDriveSin(1, -9, this);
    }
}