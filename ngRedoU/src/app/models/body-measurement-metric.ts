import { User } from "./user";
export class BodyMeasurementMetric {
  id: number;
  user: User;
  dateMeasured: Date;
  dateUpdated: Date;
  heightMM: number;
  weightKg: number;
  goalWeightKg: number;
  waistMM: number;
  neckMM: number;
  shouldersMM: number;
  chestMM: number;
  bicepMM: number;
  hipsMM: number;
  thighMM: number;

  constructor(
    id?: number,
    user?: User,
    dateMeasured?: Date,
    dateUpdated?: Date,
    heightMM?: number,
    weightKg?: number,
    goalWeightKg?: number,
    waistMM?: number,
    neckMM?: number,
    shouldersMM?: number,
    chestMM?: number,
    bicepMM?: number,
    hipsMM?: number,
    thighMM?: number
  ) {
    this.id = id;
    this.user = user;
    this.dateMeasured = dateMeasured;
    this.dateUpdated = dateUpdated;
    this.heightMM = heightMM;
    this.weightKg = weightKg;
    this.goalWeightKg = goalWeightKg;
    this.waistMM = waistMM;
    this.neckMM = neckMM;
    this.shouldersMM = shouldersMM;
    this.chestMM = chestMM;
    this.bicepMM = bicepMM;
    this.hipsMM = hipsMM;
    this.thighMM = thighMM;
  }
}
