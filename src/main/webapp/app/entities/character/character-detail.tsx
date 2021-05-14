import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './character.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICharacterDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CharacterDetail = (props: ICharacterDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { characterEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="characterDetailsHeading">Character</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{characterEntity.id}</dd>
          <dt>
            <span id="playerName">Player Name</span>
          </dt>
          <dd>{characterEntity.playerName}</dd>
          <dt>
            <span id="characterName">Character Name</span>
          </dt>
          <dd>{characterEntity.characterName}</dd>
          <dt>
            <span id="characterClass">Character Class</span>
          </dt>
          <dd>{characterEntity.characterClass}</dd>
          <dt>
            <span id="level">Level</span>
          </dt>
          <dd>{characterEntity.level}</dd>
          <dt>
            <span id="armourClass">Armour Class</span>
          </dt>
          <dd>{characterEntity.armourClass}</dd>
          <dt>
            <span id="initiative">Initiative</span>
          </dt>
          <dd>{characterEntity.initiative}</dd>
          <dt>
            <span id="hitPoints">Hit Points</span>
          </dt>
          <dd>{characterEntity.hitPoints}</dd>
          <dt>
            <span id="strength">Strength</span>
          </dt>
          <dd>{characterEntity.strength}</dd>
          <dt>
            <span id="dexterity">Dexterity</span>
          </dt>
          <dd>{characterEntity.dexterity}</dd>
          <dt>
            <span id="constitution">Constitution</span>
          </dt>
          <dd>{characterEntity.constitution}</dd>
          <dt>
            <span id="intelligence">Intelligence</span>
          </dt>
          <dd>{characterEntity.intelligence}</dd>
          <dt>
            <span id="wisdom">Wisdom</span>
          </dt>
          <dd>{characterEntity.wisdom}</dd>
          <dt>
            <span id="charisma">Charisma</span>
          </dt>
          <dd>{characterEntity.charisma}</dd>
          <dt>
            <span id="personality">Personality</span>
          </dt>
          <dd>{characterEntity.personality}</dd>
          <dt>User</dt>
          <dd>{characterEntity.user ? characterEntity.user.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/character" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/character/${characterEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ character }: IRootState) => ({
  characterEntity: character.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CharacterDetail);
