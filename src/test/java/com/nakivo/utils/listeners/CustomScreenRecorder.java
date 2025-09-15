package com.nakivo.utils.listeners;

import org.monte.media.Format;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Description.
 *
 * Author: David Le
 * Date: 9/12/2025
 * Time: 12:45 PM
 */
public class CustomScreenRecorder extends ScreenRecorder {

    private String customName;

    public CustomScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea,
                                Format fileFormat, Format screenFormat,
                                Format mouseFormat, Format audioFormat,
                                File movieFolder, String customName) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.customName = customName;
    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        }
        // Add extension automatically (.avi)
        return new File(movieFolder, customName + ".avi");
    }
}
