const navLinks = document.querySelectorAll('.nav-link');

navLinks.forEach(link => {
  link.addEventListener('click', event => {
    const href = link.href;

    event.preventDefault();

    fetch(href)
    .then(response => response.text())
    .then(data => {
      const parser = new DOMParser();
      const doc = parser.parseFromString(data, 'text/html');
      const section = doc.querySelector('section');

      document.getElementById('content').innerHTML = section.outerHTML;
    })
    .catch(error => {
      console.error('Error:', error);
    });

    // 모든 nav-link에서 active 클래스 제거
    navLinks.forEach(link => {
      link.classList.remove('active');
    });

    // 클릭된 요소에 active 클래스 추가
    event.target.classList.add('active');
  });
});