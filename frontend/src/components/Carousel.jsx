import { useState } from 'react';
import '../styles/components/Carousel.scss';

const CarouselComponent = ({ images }) => {
  const [currentIndex, setCurrentIndex] = useState(0);

  const goToPrevSlide = () => {
    setCurrentIndex(
      (prevIndex) => (prevIndex === 0 ? images.length - 1 : prevIndex - 1));
  };
  const goToNextSlide = () => {
    setCurrentIndex(
      (prevIndex) => (prevIndex === images.length - 1 ? 0 : prevIndex + 1));
  };

  return (
    <aside>
      <div id="carouselIndicators" className="carousel slide">
        <div className="carousel-indicators">
          {images.map((_, index) => (
            <button
              key={index}
              type="button"
              className={currentIndex === index ? 'active' : ''}
              onClick={() => setCurrentIndex(index)}
              aria-label={`Slide ${index + 1}`}
            ></button>
          ))}
        </div>

        <div className="carousel-inner">
          {images.map((image, index) => (
            <div key={index}
                 className={`carousel-item ${currentIndex === index ? 'active'
                   : ''}`}>
              <img src={image} className="d-block w-100"
                   alt={`Slide ${index + 1}`} />
            </div>
          ))}
        </div>

        <button className="carousel-control-prev" type="button"
                onClick={goToPrevSlide}>
          <span className="carousel-control-prev-icon"
                aria-hidden="true"></span>
          <span className="visually-hidden">Previous</span>
        </button>
        <button className="carousel-control-next" type="button"
                onClick={goToNextSlide}>
          <span className="carousel-control-next-icon"
                aria-hidden="true"></span>
          <span className="visually-hidden">Next</span>
        </button>
      </div>
    </aside>
  );
};

export default CarouselComponent;
