export class Preference {
  id: number;
  preferenceType: string;
  preferenceCategory: string;

  constructor(id?: number, preferenceType?: string, preferenceCategory?: string){
    this.id = id;
    this.preferenceType = preferenceType;
    this.preferenceCategory = preferenceCategory;
  }
}
