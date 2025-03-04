const serverAddressElem = document.getElementById("server-address");
const discordAddressElem = document.getElementById("discord-address");

//TODO: 배포 전에 각각 서버 주소와 디스코드 주소 적어넣기
const serverAddress = 'test: serverAddress';
const discordAddress = 'test: discordAddress';

serverAddressElem.addEventListener('click', () => {
  navigator.clipboard.writeText(serverAddress)
  .then(() => {
    //alert('복사 성공');
  })
  .catch(error => {
    console.error('복사 실패: ', error);
  })
});

discordAddressElem.addEventListener('click', () => {
  navigator.clipboard.writeText(discordAddress)
  .then(() => {
    //alert('복사 성공');
  })
  .catch(error => {
    console.error('복사 실패: ', error);
  })
});