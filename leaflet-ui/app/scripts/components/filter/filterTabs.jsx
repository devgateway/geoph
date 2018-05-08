import React from 'react';
import {Tabs, Tab} from 'react-bootstrap';
import FilterList from './filterListWithSearch'
import FilterDate from './filterDateRange';
import FilterSlider from './filterSliderRange';
import {connect} from 'react-redux';
import translate from '../../util/translate';
import {markApplied} from '../../util/filterUtil';

class FilterTabContent extends React.Component {
  
  render() {
    const {filters, language} = this.props;
    
    return (
      <div className="tab-container">
        <Tabs defaultActiveKey={1} animation={false}>
          <Tab className="filter-tab-content" eventKey={1}
               title={translate('filters.funding.funding') + (markApplied(filters, ["ft", "fa", "cl"]))}>
            <Tabs defaultActiveKey={1} animation={false} position="left" tabWidth={3}>
              <Tab className="filter-list-content" eventKey={1}
                   title={translate('filters.funding.fundingtypeoda') + (markApplied(filters, "ft"))}>
                <FilterList helpTextKey="help.filters.fundingtab.fundingtype" title="Funding Type (ODA)"
                            filterType="ft" {...filters["ft"]} />
              </Tab>
              <Tab className="filter-list-content" eventKey={2}
                   title={translate('filters.funding.financinginstitutionoda') + (markApplied(filters, "fa"))}>
                <FilterList helpTextKey="help.filters.fundingtab.financinginstitution"
                            title="Financing Institution (ODA)" filterType="fa" {...filters["fa"]} />
              </Tab>
              <Tab className="filter-list-content" eventKey={3}
                   title={translate('filters.funding.fundingclassification') + (markApplied(filters, "cl"))}>
                <FilterList helpTextKey="help.filters.fundingtab.fundingclassification" title="Funding Classification"
                            filterType="cl" {...filters["cl"]} />
              </Tab>
            </Tabs>
          </Tab>
          <Tab className="filter-tab-content" eventKey={2}
               title={translate('filters.agency.agency') + (markApplied(filters, "ia"))}>
            <Tabs defaultActiveKey={1} animation={false} position="left" tabWidth={3}>
              <Tab className="filter-list-content" eventKey={1}
                   title={translate('filters.agency.implementingagency') + (markApplied(filters, "ia"))}>
                <FilterList helpTextKey="help.filters.agencytab.fundingagency" title="Implementing Agency"
                            filterType="ia" {...filters["ia"]} />
              </Tab>
            </Tabs>
          </Tab>
          <Tab className="filter-tab-content" eventKey={3}
               title={translate('filters.sectors.sectors') + (markApplied(filters, ["st", "cc", "gr", "sdg", "pdp", "age"]))}>
            <Tabs defaultActiveKey={1} animation={false} position="left" tabWidth={3}>
              <Tab className="filter-list-content" eventKey={1}
                   title={translate('filters.sectors.sector') + (markApplied(filters, "st"))}>
                <FilterList helpTextKey="help.filters.sectortab.sector" title="Sector"
                            filterType="st" {...filters["st"]} />
              </Tab>
              <Tab className="filter-list-content" eventKey={2}
                   title={translate('filters.sectors.relevanceclimate') + (markApplied(filters, "cc"))}>
                <FilterList helpTextKey="help.filters.sectortab.relevanceclimate" title="Relevance to Climate"
                            filterType="cc" {...filters["cc"]} showCode={true}/>
              </Tab>
              <Tab className="filter-list-content" eventKey={3}
                   title={translate('filters.sectors.gender') + (markApplied(filters, "gr"))}>
                <FilterList helpTextKey="help.filters.sectortab.gender" title="Gender"
                            filterType="gr" {...filters["gr"]} showCode={true}/>
              </Tab>
  
              <Tab className="filter-list-content" eventKey={4}
                   title={translate('filters.sectors.sdg') + (markApplied(filters, "sdg"))}>
                <FilterList helpTextKey="help.filters.sectortab.sdg" title="SDG"
                            filterType="sdg" {...filters["sdg"]} showCode={true}/>
              </Tab>
  
              <Tab className="filter-list-content" eventKey={5}
                   title={translate('filters.sectors.pdp') + (markApplied(filters, "pdp"))}>
                <FilterList helpTextKey="help.filters.sectortab.pdp" title="PDP"
                            filterType="pdp" {...filters["pdp"]} showCode={true}/>
              </Tab>
  
              <Tab className="filter-list-content" eventKey={6}
                   title={translate('filters.sectors.agenda') + (markApplied(filters, "age"))}>
                <FilterList helpTextKey="help.filters.sectortab.agenda" title="0+10"
                            filterType="age" {...filters["age"]} showCode={true}/>
              </Tab>
            </Tabs>
          </Tab>
          <Tab className="filter-tab-content" eventKey={4}
               title={translate('filters.dates.dates') + (markApplied(filters, ["dt_start", "dt_end", "pp_start", "pp_end"]))}>
            <Tabs defaultActiveKey={1} animation={false} position="left" tabWidth={3}>
              <Tab className="filter-list-content" eventKey={1}
                   title={translate('filters.dates.implementperiodstart') + (markApplied(filters, "dt_start"))}>
                <FilterDate filterType="dt_start" helpTextKey="help.filters.datetab.implementperiodstart"
                            lang={language.lan} {...filters["dt_start"]}
                            dateMin={filters["dt_start"] ? filters["dt_start"].items[1] : ''}
                            dateMax={filters["dt_start"] ? filters["dt_start"].items[0] : ''}/>
              </Tab>
              <Tab className="filter-list-content" eventKey={2}
                   title={translate('filters.dates.implementperiodend') + (markApplied(filters, "dt_end"))}>
                <FilterDate filterType="dt_end" helpTextKey="help.filters.datetab.implementperiodend"
                            lang={language.lan} {...filters["dt_end"]}
                            dateMin={filters["dt_end"] ? filters["dt_end"].items[3] : ''}
                            dateMax={filters["dt_end"] ? filters["dt_end"].items[2] : ''}/>
              </Tab>
              <Tab className="filter-list-content" eventKey={3}
                   title={translate('filters.dates.validityperiodstart') + (markApplied(filters, "pp_start"))}>
                <FilterDate filterType="pp_start" helpTextKey="help.filters.datetab.validityperiodstart"
                            lang={language.lan} {...filters["pp_start"]}
                            dateMin={filters["pp_start"] ? filters["pp_start"].items[1] : ''}
                            dateMax={filters["pp_start"] ? filters["pp_start"].items[0] : ''}/>
              </Tab>
              <Tab className="filter-list-content" eventKey={4}
                   title={translate('filters.dates.validityperiodend') + (markApplied(filters, "pp_end"))}>
                <FilterDate filterType="pp_end" helpTextKey="help.filters.datetab.validityperiodend"
                            lang={language.lan} {...filters["pp_end"]}
                            dateMin={filters["pp_end"] ? filters["pp_end"].items[3] : ''}
                            dateMax={filters["pp_end"] ? filters["pp_end"].items[2] : ''}/>
              </Tab>
            </Tabs>
          </Tab>
          <Tab className="filter-tab-content" eventKey={5}
               title={translate('filters.status.status') + (markApplied(filters, "sa"))}>
            <Tabs defaultActiveKey={1} animation={false} position="left" tabWidth={3}>
              <Tab className="filter-list-content" eventKey={1}
                   title={translate('filters.status.financingstatus') + (markApplied(filters, "sa"))}>
                <FilterList helpTextKey="help.filters.statustab.financingstatus" title="Financing Status"
                            filterType="sa" {...filters["sa"]}/>
              </Tab>
            </Tabs>
          </Tab>
          <Tab className="filter-tab-content" eventKey={6}
               title={translate('filters.financialamount.financialamount') + (markApplied(filters, "fin_amount"))}>
            <Tabs defaultActiveKey={1} animation={false} position="left" tabWidth={3}>
              <Tab className="filter-list-content" eventKey={1}
                   title={translate('filters.financialamount.financialamount') + (markApplied(filters, "fin_amount"))}>
                <FilterSlider helpTextKey="help.filters.financialtab.financialamount" filterType="fin_amount"
                              valueSymbolPre="₱" logMarks={true} {...filters["fin_amount"]}
                              valueMin={filters["fin_amount"] ? parseInt(filters["fin_amount"].items[1]) : 0}
                              valueMax={filters["fin_amount"] ? parseInt(filters["fin_amount"].items[0]) : 100}/>
              </Tab>
            </Tabs>
          </Tab>
          <Tab className="filter-tab-content" eventKey={7}
               title={translate('filters.physical.physicalandfinancial') + (markApplied(filters, ["ph", "php"]))}>
            <Tabs defaultActiveKey={1} animation={false} position="left" tabWidth={3}>
              <Tab className="filter-list-content" eventKey={1}
                   title={translate('filters.physical.physicalProgress') + (markApplied(filters, "php"))}>
                <FilterSlider helpTextKey="help.filters.physicaltab.physicalprogress" filterType="php"
                              valueSymbolPost="%" {...filters["php"]}
                              valueMin={filters["php"] ? parseInt(filters["php"].items[1]) : 0}
                              valueMax={filters["php"] ? parseInt(filters["php"].items[0]) : 100}/>
              </Tab>
              <Tab className="filter-list-content" eventKey={2}
                   title={translate('filters.physical.physicalstatus') + (markApplied(filters, "ph"))}>
                <FilterList helpTextKey="help.filters.physicaltab.physicalstatus" title="Physical Status"
                            filterType="ph" {...filters["ph"]} />
              </Tab>
            </Tabs>
          </Tab>
        </Tabs>
      </div>
    );
    
  }
}

const mapStateToProps = (state, props) => {
  return {
    filters: state.filters.filterMain, language: state.language
  }
};

export default connect(mapStateToProps)(FilterTabContent);
