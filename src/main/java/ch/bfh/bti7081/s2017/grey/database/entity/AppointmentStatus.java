package ch.bfh.bti7081.s2017.grey.database.entity;


/**
 * Created by gabor on 17/05/17.
 */
public enum  AppointmentStatus implements AppointmentStatusOperations{
    CREATED(new CreatedAso()),
    DELAYED(new DelayedAso()),
    CANCELED(new CanceledAso()),
    FINISHED(new FinishedAso());
    }

    }

    }

    }
}
