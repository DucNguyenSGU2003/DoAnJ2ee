export class ImageGet {
  id: number ;
  imagePath: string;
  imageType: string;

  constructor(id: number , imagePath: string, imageType: string)
  {
    this.id = id;
    this.imagePath = imagePath;
    this.imageType = imageType;
  }
}
