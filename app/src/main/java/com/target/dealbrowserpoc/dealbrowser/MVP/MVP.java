package com.target.dealbrowserpoc.dealbrowser.MVP;


/**
 * Base interface for MVP.
 */

public interface MVP {
    interface View {
    }


    interface Presenter {
        void start();
        void destroy();
    }

    interface Model {

    }
}