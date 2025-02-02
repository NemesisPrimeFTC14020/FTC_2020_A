package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class BP {
    private double COUNTS_PER_MM_FORWARD = .70053521 ;
    private double COUNTS_PER_MM_SIDEWAYS = .91151321 ;
    private double COUNTS_PER_IN_ELEVATOR = 1;
    private int clawOut = 0;
    private int clawIn = 1;
    public BPHW HW = new BPHW();

    public void encDriveF(double speed, double MM, LinearOpMode OM) {
        int newATarget;
        int newBTarget;
        int newCTarget;
        int newDTarget;
        if (OM.opModeIsActive()) {
            newATarget = HW.mA.getCurrentPosition() + (int) (MM * COUNTS_PER_MM_FORWARD);
            newBTarget = HW.mB.getCurrentPosition() + (int) (MM * COUNTS_PER_MM_FORWARD);
            newCTarget = HW.mC.getCurrentPosition() + (int) (MM * COUNTS_PER_MM_FORWARD);
            newDTarget = HW.mD.getCurrentPosition() + (int) (MM * COUNTS_PER_MM_FORWARD);
            HW.mA.setTargetPosition(newATarget);
            HW.mB.setTargetPosition(newBTarget);
            HW.mC.setTargetPosition(newCTarget);
            HW.mD.setTargetPosition(newDTarget);
            HW.mA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.mB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.mC.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.mD.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.mA.setPower(Math.abs(speed));
            HW.mB.setPower(Math.abs(speed));
            HW.mC.setPower(Math.abs(speed));
            HW.mD.setPower(Math.abs(speed));
            while (OM.opModeIsActive() &&
                    (HW.mA.isBusy() && HW.mB.isBusy() && HW.mC.isBusy() && HW.mD.isBusy())) {
                OM.telemetry.addData("Path1", "Running to %7d :%7d :%7d :%7d", newATarget, newBTarget, newCTarget, newDTarget);
                OM.telemetry.update();
            }
            HW.mA.setPower(0);
            HW.mB.setPower(0);
            HW.mC.setPower(0);
            HW.mD.setPower(0);
            HW.mA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HW.mB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HW.mC.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HW.mD.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void encDriveS(double speed, double MM, LinearOpMode OM) {
        int newATarget;
        int newBTarget;
        int newCTarget;
        int newDTarget;
        if (OM.opModeIsActive()) {
            newATarget = HW.mA.getCurrentPosition() + (int) (MM * COUNTS_PER_MM_SIDEWAYS);
            newBTarget = HW.mB.getCurrentPosition() - (int) (MM * COUNTS_PER_MM_SIDEWAYS);
            newCTarget = HW.mC.getCurrentPosition() + (int) (MM * COUNTS_PER_MM_SIDEWAYS);
            newDTarget = HW.mD.getCurrentPosition() - (int) (MM * COUNTS_PER_MM_SIDEWAYS);
            HW.mA.setTargetPosition(newATarget);
            HW.mB.setTargetPosition(newBTarget);
            HW.mC.setTargetPosition(newCTarget);
            HW.mD.setTargetPosition(newDTarget);
            HW.mA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.mB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.mC.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.mD.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.mA.setPower(Math.abs(speed));
            HW.mB.setPower(Math.abs(speed));
            HW.mC.setPower(Math.abs(speed));
            HW.mD.setPower(Math.abs(speed));
            while (OM.opModeIsActive() &&
                    (HW.mA.isBusy() && HW.mB.isBusy() && HW.mC.isBusy() && HW.mD.isBusy())) {
                OM.telemetry.addData("Path1", "Running to %7d :%7d :%7d :%7d", newATarget, newBTarget, newCTarget, newDTarget);
                OM.telemetry.update();
            }
            HW.mA.setPower(0);
            HW.mB.setPower(0);
            HW.mC.setPower(0);
            HW.mD.setPower(0);
            HW.mA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HW.mB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HW.mC.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HW.mD.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void encDriveFin(double speed, double in, LinearOpMode OM) {
        double MM = 25.4 * in;
        double C = 1;
        // never forget to add the constant C
        encDriveF(speed, C * MM, OM);
    }

    public void encDriveSin(double speed, double in, LinearOpMode OM) {
        double MM = 25.4 * in;
        encDriveS(speed, MM, OM);
    }

    public void encDriveMecanum(double speed, double Xmm, double Ymm, LinearOpMode OM) {
        int newATarget;
        int newBTarget;
        int newCTarget;
        int newDTarget;
        if (OM.opModeIsActive()) {
            newATarget = HW.mA.getCurrentPosition() + (int) (Ymm * COUNTS_PER_MM_FORWARD) + (int) (Xmm * COUNTS_PER_MM_SIDEWAYS);
            newBTarget = HW.mB.getCurrentPosition() + (int) (Ymm * COUNTS_PER_MM_FORWARD) - (int) (Xmm * COUNTS_PER_MM_SIDEWAYS);
            newCTarget = HW.mC.getCurrentPosition() + (int) (Ymm * COUNTS_PER_MM_FORWARD) + (int) (Xmm * COUNTS_PER_MM_SIDEWAYS);
            newDTarget = HW.mD.getCurrentPosition() + (int) (Ymm * COUNTS_PER_MM_FORWARD) - (int) (Xmm * COUNTS_PER_MM_SIDEWAYS);
            HW.mA.setTargetPosition(newATarget);
            HW.mB.setTargetPosition(newBTarget);
            HW.mC.setTargetPosition(newCTarget);
            HW.mD.setTargetPosition(newDTarget);
            HW.mA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.mB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.mC.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.mD.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.mA.setPower(Math.abs(speed));
            HW.mB.setPower(Math.abs(speed));
            HW.mC.setPower(Math.abs(speed));
            HW.mD.setPower(Math.abs(speed));
            while (OM.opModeIsActive() &&
                    (HW.mA.isBusy() && HW.mB.isBusy() && HW.mC.isBusy() && HW.mD.isBusy())) {
                OM.telemetry.addData("Path1", "Running to %7d :%7d :%7d :%7d", newATarget, newBTarget, newCTarget, newDTarget);
                OM.telemetry.update();
            }
            HW.mA.setPower(0);
            HW.mB.setPower(0);
            HW.mC.setPower(0);
            HW.mD.setPower(0);
            HW.mA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HW.mB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HW.mC.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HW.mD.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }

    public void encDriveMecanumin(double speed, double Xin, double Yin, LinearOpMode OM) {
        double Xmm = Xin * 25.4;
        double Ymm = Yin * 25.4;
        encDriveMecanum(speed, Xmm, Ymm, OM);
    }

    public void gyroTurn(double speed, double angle) {
        double C = 0.9;
        if (angle < 0) speed = -speed;
      while (Math.abs(getHeading()) < Math.abs(C * angle)) {
          HW.mA.setPower(speed);
          HW.mB.setPower(-speed);
          HW.mC.setPower(-speed);
          HW.mD.setPower(speed);
      }
        HW.mA.setPower(0);
        HW.mA.setPower(0);
        HW.mA.setPower(0);
        HW.mA.setPower(0);
    }
    public void gyroTurnTo(double speed, double angle) {
        double C = 0.9;
        double target = getHeading() + angle;
        double error = target - getHeading();
        if (angle < 0) speed = -speed;
        while (error < 1) {
            speed = speed*(.2*error);
            HW.mA.setPower(speed);
            HW.mB.setPower(-speed);
            HW.mC.setPower(-speed);
            HW.mD.setPower(speed);
        }
        HW.mA.setPower(0);
        HW.mA.setPower(0);
        HW.mA.setPower(0);
        HW.mA.setPower(0);
    }

    /*public void yClaw(char direction, double in, LinearOpMode OM) {
        double MM = 25.4 * in;
        int d = 1;
        if (direction == '-') d = -1;
        double C = 1;

        HW.elevator.setTargetPosition(HW.elevator.getCurrentPosition() - (int) (C * MM * d * COUNTS_PER_IN_ELEVATOR));
        HW.elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        HW.elevator.setPower(0.7);
        while (OM.opModeIsActive() && HW.elevator.isBusy()) {
            OM.telemetry.addData("Running to", HW.elevator.getTargetPosition());
            OM.telemetry.update();
        }
        HW.elevator.setPower(0);
        HW.elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
*/
    public void yClawTime(char direction, double in, LinearOpMode OM) {
        HW.elevator.setDirection(DcMotorSimple.Direction.FORWARD);
        if (direction == '-') HW.elevator.setDirection(DcMotorSimple.Direction.REVERSE);
        long time_per_in = 1333;
        long time = Math.round(in * time_per_in);

        HW.elevator.setPower(0.3);
        OM.sleep(time);
        HW.elevator.setPower(0);
    }

    public void curveClaw(double pos) {
        HW.armServo.setPosition(pos);
    }

    public void Claw(char direction) {
        if (direction == '-') HW.foundationServo.setPosition(clawOut);
        else if (direction == '+') HW.foundationServo.setPosition(clawIn);
    }

    public static double[] mecPower(double iX, double iY, double iR) {
        if (Math.abs(iX) <= 0.05) iX = 0;
        if (Math.abs(iY) <= 0.05) iY = 0;
        if (Math.abs(iR) <= 0.05) iR = 0;
        //acquire three desired movement inputs from the
        // driver, y translation, x translation, rotational mostion
        double pA = iR + iY + iX;
        double pB = -iR + iY - iX;
        double pC = -iR + iY + iX;
        double pD = iR + iY - iX;
        //each axis of motion corresponds to a forward
        // or backwards drive of a specific motor.
        // Add or subtract these values to combine all inputs
        double max = Math.abs(Math.max(1, pA));
        max = Math.abs(Math.max(max, pB));
        max = Math.abs(Math.max(max, pC));
        max = Math.abs(Math.max(max, pD));
        pA /= max;
        pB /= max;
        pC /= max;
        pD /= max;
        //double[] returnVal = new double[]();
        return new double[]{pA, pB, pC, pD, 0, 0};
    }

    public static double[] mecPowerField(double iX, double iY, double iR, double offset, LinearOpMode OM, double robotAngle) {
        if (Math.abs(iX) <= 0.05) iX = 0;
        if (Math.abs(iY) <= 0.05) iY = 0;
        if (Math.abs(iR) <= 0.05) iR = 0;
        double pA;
        double pB;
        double pC;
        double pD;
        double Y = iY*Math.cos(-Math.toRadians(robotAngle + offset)) + iX*Math.sin(-Math.toRadians(robotAngle + offset));
        double X = -iY*Math.sin(-Math.toRadians(robotAngle + offset)) + iX*Math.cos(-Math.toRadians(robotAngle + offset));
        //acquire three desired movement inputs from the
        // driver, y translation, x translation, rotational mostion
        pA = iR + Y + X;
        pB = -iR + Y - X;
        pC = -iR + Y + X;
        pD = iR + Y - X;
        //each axis of motion corresponds to a forward
        // or backwards drive of a specific motor.
        // Add or subtract these values to combine all inputs
        double max = Math.abs(Math.max(1, pA));
        max = Math.abs(Math.max(max, pB));
        max = Math.abs(Math.max(max, pC));
        max = Math.abs(Math.max(max, pD));
        pA /= max;
        pB /= max;
        pC /= max;
        pD /= max;
        return new double[]{pA, pB, pC, pD, X, Y};
    }
   public double getHeading() {
        double rawAngle = Math.toDegrees(HW.imu.getAngularOrientation().firstAngle);
        return(rawAngle);
    }

    private Recognition searchForSkystone(TFObjectDetector tfod, Recognition latest) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            for (Recognition recognition : updatedRecognitions) {
                if (recognition.getLabel().equals("Skystone")) {
                    latest = recognition;
                    return recognition;
                }
            }
            return latest;
    }

   public void scanForSkystone(TFObjectDetector tfod, DcMotor leftDrive, DcMotor rightDrive) {
        boolean inBounds = false;
        Recognition latest = null;
        double D = 1;
        double threshold = 0.05;
        int FOV = 78;

        while (D > threshold) {
            Recognition skystone = searchForSkystone(tfod, latest);
            if (skystone == null) continue;
            skystone.estimateAngleToObject(AngleUnit.DEGREES);
            D = 4 * Math.toDegrees(Math.tan(skystone.getWidth()/1280 * FOV));

        }
   }
   public void getTFodDistances( int leftPixel, int rightPixel, int imageWidth) {
        double FOV;
        //double angleLeft = Math.atan((imageWidth/2 - leftPixel)/)
   }
}
