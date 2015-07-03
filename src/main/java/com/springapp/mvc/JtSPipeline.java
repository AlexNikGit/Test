package com.springapp.mvc;

/**
 * Основной класс ядра конвейера. ...
 */
public class JtSPipeline {
    private void PipeleneStart( ) {      // отвечает за запуск конвейера

    }
    private void PipelineStop( ) {       // отвечает за остановку работы конвейера

    }

    /* Класс определяющий базовый функционал абстрактного (любого) конвейера и реализует базовый интерфейс конвейера.
     *  */
    private static abstract class BasePipelineImpl implements BasePipeline {

    }
    private static class ViewPipeline extends BasePipelineImpl {

    }
    private static class ModelPipeline extends BasePipelineImpl {

    }
}
