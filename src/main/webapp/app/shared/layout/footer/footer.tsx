import './footer.scss';

import React from 'react';

import { Col, Row } from 'reactstrap';

const Footer = props => (
  <div className="footer page-content">
    <Row>
      <Col md="12">
        <p>By Sonia Orlikowska and Marcin Marszałek</p>
      </Col>
    </Row>
  </div>
);

export default Footer;
