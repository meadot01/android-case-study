package com.target.dealbrowserpoc.dealbrowser;


/**
 * Base interface for MVP.
 */

public interface MVP {
    public interface View {
    }


    public interface Presenter {
        void start();
        void destroy();
    }

    public interface Model {

    }
}