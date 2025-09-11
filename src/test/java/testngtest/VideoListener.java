package testngtest;

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

public class VideoListener implements ITestListener {
    private ScreenRecorder screenRecorder;

    @Override
    public void onTestStart(ITestResult result) {
        try {
            // Detect if running in headless environment
            if (GraphicsEnvironment.isHeadless()) {
                System.out.println("[VideoListener] Headless environment detected. Skipping video recording.");
                return;
            }

            GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

            File videoDir = new File("videos");
            if (!videoDir.exists()) {
                videoDir.mkdirs();
            }

            screenRecorder = new ScreenRecorder(gc,
                gc.getBounds(),
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO,
                    EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                    CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                    DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                    QualityKey, 1.0f,
                    KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.AUDIO,
                    EncodingKey, ENCODING_PCM_SIGNED,
                    FrameRateKey, new Rational(44100, 1),
                    SampleSizeInBitsKey, 16, ChannelsKey, 2,
                    SignedKey, true),
                null,
                videoDir);

            screenRecorder.start();
            System.out.println("[VideoListener] Started recording for test: " + result.getName());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[VideoListener] Failed to start recording: " + e.getMessage());
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
            if (screenRecorder != null) {
                screenRecorder.stop();
                System.out.println("[VideoListener] Video saved for " + result.getName() + " [" + status + "]");
            }
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
