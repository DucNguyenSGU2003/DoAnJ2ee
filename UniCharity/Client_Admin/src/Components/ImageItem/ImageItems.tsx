import React from 'react'
import ImageItem from './ImageItem'
import { ImageGet } from '../../Models/Image'

type Props = {
    images: ImageGet[]
    handleDelete: (idImgae: number) => void
}

const ImageItems = ({ images, handleDelete }: Props) => {
    return (
        <>
            {images.map((image, index) => (
                <ImageItem
                    handleDelete={handleDelete}
                    key={image.id}
                    image={image}
                    index={index + 1}
                />
            ))}
        </>
    )
}

export default ImageItems
