export interface IGeocity {
  id?: number;
  name?: string;
  asciiname?: string;
  alternatenames?: string;
  location?: number;
  lat?: number;
  lon?: number;
  featureclass?: string;
  featuretype?: string;
  countrycode?: string;
  cc2?: string;
  admin1code?: string;
  admin2code?: string;
  admin3code?: string;
  admin4code?: string;
  population?: number;
  elevation?: number;
  dem?: string;
}

export class Geocity implements IGeocity {
  constructor(
    public id?: number,
    public name?: string,
    public asciiname?: string,
    public alternatenames?: string,
    public location?: number,
    public lat?: number,
    public lon?: number,
    public featureclass?: string,
    public featuretype?: string,
    public countrycode?: string,
    public cc2?: string,
    public admin1code?: string,
    public admin2code?: string,
    public admin3code?: string,
    public admin4code?: string,
    public population?: number,
    public elevation?: number,
    public dem?: string
  ) {}
}
