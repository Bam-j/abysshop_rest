import { useState } from 'react';
import { Link } from 'react-router-dom';

import logo from '../../assets/images/abyssblock_square_64x64.png';
import '../../styles/components/common/Footer.scss';

const Footer = () => {
  const [serverAddress] = useState('서버 주소');
  const [discordAddress] = useState('디스코드 주소');

  const copyAddressToClipboard = address => {
    navigator.clipboard.writeText(address);
  };

  return (
    <footer className="footer">
      <div className="square-logo">
        <Link to="/">
          <img src={logo} alt="어비스 블록 미니멀 로고" />
        </Link>
      </div>

      <ul className="address-menu">
        <li id="server-address"
            onClick={() => copyAddressToClipboard(serverAddress)}
        >서버 주소
        </li>
        <li id="discord-address"
            onClick={() => copyAddressToClipboard(discordAddress)}>디스코드
        </li>
      </ul>
    </footer>
  );
};

export default Footer;
