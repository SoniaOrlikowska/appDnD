import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { setFileData, byteSize, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './character.reducer';
import { ICharacter } from 'app/shared/model/character.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICharacterUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CharacterUpdate = (props: ICharacterUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { characterEntity, users, loading, updating } = props;

  const { personality } = characterEntity;

  const handleClose = () => {
    props.history.push('/character');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getUsers();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...characterEntity,
        ...values,
        user: users.find(it => it.id.toString() === values.userId.toString()),
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="myApp.character.home.createOrEditLabel" data-cy="CharacterCreateUpdateHeading">
            Create or edit a Character
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : characterEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="character-id">ID</Label>
                  <AvInput id="character-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="playerNameLabel" for="character-playerName">
                  Player Name
                </Label>
                <AvField id="character-playerName" data-cy="playerName" type="text" name="playerName" />
              </AvGroup>
              <AvGroup>
                <Label id="characterNameLabel" for="character-characterName">
                  Character Name
                </Label>
                <AvField id="character-characterName" data-cy="characterName" type="text" name="characterName" />
              </AvGroup>
              <AvGroup>
                <Label id="characterClassLabel" for="character-characterClass">
                  Character Class
                </Label>
                <AvField id="character-characterClass" data-cy="characterClass" type="text" name="characterClass" />
              </AvGroup>
              <AvGroup>
                <Label id="levelLabel" for="character-level">
                  Level
                </Label>
                <AvField id="character-level" data-cy="level" type="string" className="form-control" name="level" />
              </AvGroup>
              <AvGroup>
                <Label id="armourClassLabel" for="character-armourClass">
                  Armour Class
                </Label>
                <AvField id="character-armourClass" data-cy="armourClass" type="string" className="form-control" name="armourClass" />
              </AvGroup>
              <AvGroup>
                <Label id="initiativeLabel" for="character-initiative">
                  Initiative
                </Label>
                <AvField id="character-initiative" data-cy="initiative" type="string" className="form-control" name="initiative" />
              </AvGroup>
              <AvGroup>
                <Label id="hitPointsLabel" for="character-hitPoints">
                  Hit Points
                </Label>
                <AvField id="character-hitPoints" data-cy="hitPoints" type="string" className="form-control" name="hitPoints" />
              </AvGroup>
              <AvGroup>
                <Label id="strengthLabel" for="character-strength">
                  Strength
                </Label>
                <AvField id="character-strength" data-cy="strength" type="string" className="form-control" name="strength" />
              </AvGroup>
              <AvGroup>
                <Label id="dexterityLabel" for="character-dexterity">
                  Dexterity
                </Label>
                <AvField id="character-dexterity" data-cy="dexterity" type="string" className="form-control" name="dexterity" />
              </AvGroup>
              <AvGroup>
                <Label id="constitutionLabel" for="character-constitution">
                  Constitution
                </Label>
                <AvField id="character-constitution" data-cy="constitution" type="string" className="form-control" name="constitution" />
              </AvGroup>
              <AvGroup>
                <Label id="intelligenceLabel" for="character-intelligence">
                  Intelligence
                </Label>
                <AvField id="character-intelligence" data-cy="intelligence" type="string" className="form-control" name="intelligence" />
              </AvGroup>
              <AvGroup>
                <Label id="wisdomLabel" for="character-wisdom">
                  Wisdom
                </Label>
                <AvField id="character-wisdom" data-cy="wisdom" type="string" className="form-control" name="wisdom" />
              </AvGroup>
              <AvGroup>
                <Label id="charismaLabel" for="character-charisma">
                  Charisma
                </Label>
                <AvField id="character-charisma" data-cy="charisma" type="string" className="form-control" name="charisma" />
              </AvGroup>
              <AvGroup>
                <Label id="personalityLabel" for="character-personality">
                  Personality
                </Label>
                <AvInput id="character-personality" data-cy="personality" type="textarea" name="personality" />
              </AvGroup>
              <AvGroup>
                <Label for="character-user">User</Label>
                <AvInput id="character-user" data-cy="user" type="select" className="form-control" name="userId">
                  <option value="" key="0" />
                  {users
                    ? users.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/character" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  users: storeState.userManagement.users,
  characterEntity: storeState.character.entity,
  loading: storeState.character.loading,
  updating: storeState.character.updating,
  updateSuccess: storeState.character.updateSuccess,
});

const mapDispatchToProps = {
  getUsers,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CharacterUpdate);
