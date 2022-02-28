package curtin.edu.shrinkage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.os.Bundle;

import com.google.android.datatransport.runtime.ExecutionModule_ExecutorFactory;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class ScannerActivity2 extends AppCompatActivity {
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    //private ImageCapture imageCapture;

    private PreviewView previewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_scanner2);
        previewView = findViewById(R.id.previewView);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try
            {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch(ExecutionException | InterruptedException e){}

        }, ContextCompat.getMainExecutor(this));

/*
        PreviewView previewView = findViewById(R.id.previewView);

        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                // Camera provider is now guaranteed to be available
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                // Set up the view finder use case to display camera preview
                Preview preview = new Preview.Builder().build();

                // Set up the capture use case to allow users to take photos
                imageCapture = new ImageCapture.Builder()
                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                        .build();

                // Choose the camera by requiring a lens facing
                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(lensFacing)
                        .build();

                // Attach use cases to the camera with the same lifecycle owner
                Camera camera = cameraProvider.bindToLifecycle(
                        ((LifecycleOwner) this),
                        cameraSelector,
                        preview,
                        imageCapture);

                // Connect the preview use case to the previewView
                preview.setSurfaceProvider(
                        previewView.getSurfaceProvider());
            } catch (InterruptedException | ExecutionException e) {
                // Currently no exceptions thrown. cameraProviderFuture.get()
                // shouldn't block since the listener is being called, so no need to
                // handle InterruptedException.
            }
        }, ContextCompat.getMainExecutor(this));

 */
    }

    void bindPreview(@NonNull ProcessCameraProvider cameraProvider)
    {
        previewView.setImplementationMode(PreviewView.ImplementationMode.PERFORMANCE);
        Preview preview = new Preview.Builder()
                .build();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview);
    }
}