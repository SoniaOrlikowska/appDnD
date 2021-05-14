import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { byteSize, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Grid, Cell } from 'react-flexr';
import 'react-flexr/styles.css';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './character.reducer';
import { ICharacter } from 'app/shared/model/character.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICharacterProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Character = (props: ICharacterProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { characterList, match, loading } = props;
  return (
    <div>
      <h2 id="character-heading" data-cy="CharacterHeading">
        Characters
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Character
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {characterList && characterList.length > 0 ? (
          <Table responsive>
            <div>
              <thead>
                <tr>
                  <th>Player Name</th>
                  <th>Character Name</th>
                  <th>Character Class</th>
                  <th>Level</th>
                  <th>Armour Class</th>
                  <th>Initiative</th>
                  <th>Hit Points</th>
                  <th>Strength</th>
                  <th>Dexterity</th>
                  <th>Constitution</th>
                  <th>Intelligence</th>
                  <th>Wisdom</th>
                  <th>Charisma</th>
                  <th>Personality</th>
                  <th>User</th>
                  <th>ID</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {characterList.map((character, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>{character.playerName}</td>
                    <td>{character.characterName}</td>
                    <td>{character.characterClass}</td>
                    <td>{character.level}</td>
                    <td>{character.armourClass}</td>
                    <td>{character.initiative}</td>
                    <td>{character.hitPoints}</td>
                    <td>{character.strength}</td>
                    <td>{character.dexterity}</td>
                    <td>{character.constitution}</td>
                    <td>{character.intelligence}</td>
                    <td>{character.wisdom}</td>
                    <td>{character.charisma}</td>
                    <td>{character.personality}</td>
                    <td>{character.user ? character.user.id : ''}</td>
                    <td>
                      <Button tag={Link} to={`${match.url}/${character.id}`} color="link" size="sm">
                        {character.id}
                      </Button>
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${character.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${character.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${character.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </div>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Characters found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ character }: IRootState) => ({
  characterList: character.entities,
  loading: character.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Character);
