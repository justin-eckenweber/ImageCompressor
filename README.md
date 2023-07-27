# ImageCompressor

Welcome to ImageCompressor, a tool designed to compress images within a folder effortlessly.

## How to Use
Follow these simple steps to compress your images in no time:

---

### Folders

1. Place the ImageCompressor.jar file in a folder. Ensure that this folder is located above the folder containing the images you want to compress.

   For example:

```
└── myfolder
    ├── ImageCompressor.jar
    └── MyFolderWithImages
        ├── image1.png
        └── image2.png
```

2. Run the ImageCompressor.jar using the console.

   Command: `java -jar ./ImageCompressor.jar MyFolderWithImages`

3. Retrieve the Compressed Files

   The compressed files will be conveniently located in `/myfolder/MyFolderWithImages/compressed`.
```
└── myfolder
    ├── ImageCompressor.jar
    └── MyFolderWithImages
        ├── image1.png
        ├── image2.png
        └── compressed
            ├── image1.jpg
            └── image2.jpg
```
---

### Single file

1. Place the ImageCompressor.jar file in a folder containing an image you want to compress.
```
└── myfolder
   ├── ImageCompressor.jar
   └── image1.png
```
2. Run the ImageCompressor.jar in the console. Command: `java -jar ./ImageCompressor.jar ./image1.png`
3. Retrieve the compressed file. It will be located in `/myfolder/compressed`
```
└── myfolder
    ├── ImageCompressor.jar
    ├── image1.png
    └── compressed
        └── image1.jpg
```

## Jar Parameters

Please take note of the following instructions regarding jar parameters. Keep in mind that the folder name should always be the first argument. If you wish to compress all child folders, you can simply enter `all` instead of a specific folder name.

- `no-resize`: This parameter ensures that the image size remains unchanged, without any reduction.
- `custom-size:WIDTHxHEIGHT`: Specify a custom image size using this format. For example, `custom-size:1280x720`.
- `quality:FLOAT`: Set the compression quality ranging from 0.0 to 1.0 to achieve the desired level of compression.
- `keep-file-type`: Keeps the given file type and does not convert it to a jpg.

## More examples

1. To compress images without resizing and with a custom compression quality of 0.8:
   ```
   java -jar ./ImageCompressor.jar MyFolderWithImages no-resize quality:0.8
   ```

2. To compress images with a custom size of 800x600 pixels and a compression quality of 0.6:
   ```
   java -jar ./ImageCompressor.jar MyFolderWithImages custom-size:800x600 quality:0.6
   ```

3. To compress all child folders within the specified folder, without resizing and with the default compression quality:
   ```
   java -jar ./ImageCompressor.jar all no-resize
   ```

4. To compress a png file and keep its file type to keep transparency:
   ```
   java -jar ./ImageCompressor.jar ./MyImage.png keep-file-type
   ```

Remember to adjust the parameters and folder names according to your requirements. Experiment with different combinations to get the desired results.