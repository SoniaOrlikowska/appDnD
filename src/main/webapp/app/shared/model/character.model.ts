import { IUser } from 'app/shared/model/user.model';

export interface ICharacter {
  id?: string;
  playerName?: string | null;
  characterName?: string | null;
  characterClass?: string | null;
  level?: number | null;
  armourClass?: number | null;
  initiative?: number | null;
  hitPoints?: number | null;
  strength?: number | null;
  dexterity?: number | null;
  constitution?: number | null;
  intelligence?: number | null;
  wisdom?: number | null;
  charisma?: number | null;
  personality?: string | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<ICharacter> = {};
