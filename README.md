# Image Classification Predictor (DJL)

A Java-based machine learning application that uses **Deep Java Library (DJL)** and **PyTorch** to identify objects within images. The project utilizes a pre-trained **ResNet** model to analyze an image and return the top 5 most likely classifications with their respective probability scores.

## 🧠 How it Works

1. **Preprocessing**: The image is resized to  pixels and converted to a numerical Tensor.
2. **Inference**: The PyTorch engine loads a pre-trained ResNet model from the DJL Model Zoo.
3. **Output**: The model calculates the Softmax probability for thousands of object categories and returns the top 5 hits.

---


Gemini said
Based on the image of your project, you are using a specific subfolder structure within your resources. Here is the updated Project Structure section for your README.md to reflect your exact setup.

📂 Project Structure
To ensure the PathMatchingResourcePatternResolver correctly locates your images, follow the directory structure shown below. Your images are stored in the resources/images directory.

image-predictor/
├── src/
│   └── main/
│       ├── java/
│       │   └── com.abhi.imagep/
│       │       └── ImagePredictor.java   <-- Your Main Class
│       └── resources/
│           └── images/                   <-- Image Storage Location
│               ├── book.jpg
│               ├── coffee_mug.jpg
│               ├── laptop.jpg
│               ├── pill_bottle.png       <-- Default Image
│               └── ... (other images)
├── pom.xml                               <-- Maven Dependencies
└── README.md

---

## 🛠️ Prerequisites

* **JDK 17** or higher.
* **Maven 3.x**.

---

## 🚀 How to Run

### 1. Place your Image

Move the image you want to analyze into the `src/main/resources/images` folder.

### 2. Build the Project

Open your terminal in the project root and run:

```bash
mvn clean install

```

### 3. Execute the Predictor

You can run the main class directly. By default, it looks for `pill_bottle.png`.

**Using Maven:**

```bash
# Default image
mvn exec:java -Dexec.mainClass="ImagePredictor"

# Custom image path (relative to resources)
mvn exec:java -Dexec.mainClass="ImagePredictor" -Dexec.args="images/my_cat.jpg"

```

---

## 📊 Example Output

When processing an image of a medicine container, the console will display:

```text
Top 5 Predictions:
[class: "n03937543 pill bottle", probability: 0.93217]
[class: "n03825788 nipple", probability: 0.03130]
[class: "n04557648 water bottle", probability: 0.02368]
[class: "n04560804 water jug", probability: 0.00278]
[class: "n03690938 lotion", probability: 0.00173]

```

---

## 📦 Dependencies

Ensure your `pom.xml` includes the following DJL stacks:

* `ai.djl:api`
* `ai.djl.pytorch:pytorch-engine`
* `ai.djl.pytorch:pytorch-model-zoo`
* `org.springframework:spring-core` (for the Resource Resolver)

---

**Would you like me to help you add a specialized `Translator` to handle different image aspect ratios more accurately?**
