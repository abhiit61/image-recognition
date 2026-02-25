package com.abhi.imagep;

import java.io.IOException;
import java.io.InputStream;

import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.transform.Resize;
import ai.djl.modality.cv.transform.ToTensor;
import ai.djl.modality.cv.translator.ImageClassificationTranslator;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class ImagePredictor {

  public static void main(String[] args) throws IOException, ModelException, TranslateException {

    // Path to your image file
    String imagePath = "pill_bottle.png";
    if(args.length > 0) {
      imagePath = args[0];
    }

    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    Resource resource = resolver.getResource("classpath:"+imagePath);

    InputStream is = resource.getInputStream();
    // Load image
    Image img = ImageFactory.getInstance().fromInputStream(is);

    // Run prediction
    Classifications predictions = predict(img);

    // Print results
    System.out.println("Top 5 Predictions:");
    predictions.topK(5).forEach(System.out::println);

  }

  public static Classifications predict(Image image) throws IOException, ModelException, TranslateException {
    // Define translator (preprocessing)
    Translator<Image, Classifications> translator = ImageClassificationTranslator.builder()
        .addTransform(new Resize(224, 224))
        .addTransform(new ToTensor())
        .optApplySoftmax(true)
        .build();

    // Updated criteria to use explicit model from TorchHub
    Criteria<Image, Classifications> criteria = Criteria.builder()
        .setTypes(Image.class, Classifications.class)
        .optTranslator(translator)
        .optArtifactId("resnet") // Use explicit model name
        .optEngine("PyTorch") // Specify engine
        .build();

    try (ZooModel<Image, Classifications> model = criteria.loadModel();
        Predictor<Image, Classifications> predictor = model.newPredictor()) {
      return predictor.predict(image);
    }
  }

}
