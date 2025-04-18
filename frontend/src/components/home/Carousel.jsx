import { useState } from 'react';

import '../../styles/components/home/Carousel.scss';

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
      <div id="carouselIndicators" className="carousel">
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

        <button className="carousel-control-prev" onClick={goToPrevSlide}>
          <span className="custom-arrow prev-arrow" aria-hidden="true"></span>
        </button>
        <button className="carousel-control-next" onClick={goToNextSlide}>
          <span className="custom-arrow next-arrow" aria-hidden="true"></span>
        </button>
      </div>
    </aside>
  );
};

export default CarouselComponent;
