package com.nakivo.listeners;

import com.nakivo.utils.Utils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoListenerWithUI implements ITestListener {
    private ScreenRecorder screenRecorder;

    @Override
    public void onTestStart(ITestResult result) {
        try {
            GraphicsConfiguration gc = GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();

            screenRecorder = new CustomScreenRecorder(gc,
                    gc.getBounds(),
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_QUICKTIME),
                    new Format(MediaTypeKey, MediaType.VIDEO,
                            EncodingKey, ENCODING_QUICKTIME_JPEG,
                            CompressorNameKey, ENCODING_QUICKTIME_JPEG,
                            DepthKey, 24,
                            FrameRateKey, Rational.valueOf(15),
                            QualityKey, 1.0f,
                            KeyFrameIntervalKey, 15 * 60),
                    null,
                    null,
                    new File("build/reports/videos"),
                    Utils.generateFileName(result.getName()));
            screenRecorder.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        stopRecording(result, "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        stopRecording(result, "FAILED");
    }

    private void stopRecording(ITestResult result, String status) {
        try {
            screenRecorder.stop();
            System.out.println("Video saved for " + result.getName() + " [" + status + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onTestFailedWithTimeout(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
