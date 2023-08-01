package prat.learning.recipebook.Converter;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import prat.learning.recipebook.Command.UomCommand;
import prat.learning.recipebook.Model.UnitOfMeasure;
@Component
public class UomToUomCommand implements Converter<UnitOfMeasure, UomCommand> {
    @Override
    @Nullable
    @Synchronized
    public UomCommand convert(UnitOfMeasure unitOfMeasure) {
        if(unitOfMeasure!=null){
            final UomCommand uomc=new UomCommand();
            uomc.setId(unitOfMeasure.getId());
            uomc.setUom(unitOfMeasure.getUom());
            return uomc;
        }
        return null;
    }
}
